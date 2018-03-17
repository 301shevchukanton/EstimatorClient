package estimator.kissteam.com.estimatorclient.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import estimator.kissteam.com.estimatorclient.dal.gateway.issue.GetIssuesGateway
import estimator.kissteam.com.estimatorclient.dal.gateway.room.GetRoomsGateway
import estimator.kissteam.com.estimatorclient.view.recycler.RoomItem
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by AntonShevchuk on 16.03.2018.
 */
class RoomsViewModel : ViewModel() {

	val roomLiveData: MutableLiveData<List<RoomItem>> = MutableLiveData()

	init {
		Observable.interval(5, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.io())
				.flatMap {
					GetRoomsGateway().execute()
				}
				.map { rooms ->
					val roomItems = mutableListOf<RoomItem>().apply {
						rooms.forEach { room ->
							val issues = GetIssuesGateway(room.id)
									.execute()
									.blockingFirst()
							var containNullEstimate = false
							var estimatesSum: Float = 0f
							issues.forEach { issue ->
								if (issue.estimate == null) {
									containNullEstimate = true
								} else {
									estimatesSum += issue.estimate
								}
							}
							val totalEstimate: Float? = if (containNullEstimate) {
								null
							} else {
								estimatesSum / issues.size
							}
							add(RoomItem(room, issues, totalEstimate))
						}
					}
					return@map roomItems
				}
				.distinctUntilChanged()
				.observeOn(AndroidSchedulers.mainThread())
				.onErrorReturn { mutableListOf() }
				.subscribe {
					roomLiveData.value = it
				}
	}
}