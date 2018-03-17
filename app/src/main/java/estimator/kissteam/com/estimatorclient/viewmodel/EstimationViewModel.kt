package estimator.kissteam.com.estimatorclient.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import estimator.kissteam.com.estimatorclient.dal.entities.Issue
import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.dal.gateway.estimate.CreateOrUpdateEstimateGateway
import estimator.kissteam.com.estimatorclient.dal.gateway.estimate.GetEstimatesGateway
import estimator.kissteam.com.estimatorclient.dal.gateway.issue.GetIssuesGateway
import estimator.kissteam.com.estimatorclient.dal.gateway.user.GetCurrentUserGateway
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by AntonShevchuk on 16.03.2018.
 */
class EstimationViewModel : ViewModel() {

	val tasksLiveData: MutableLiveData<List<Issue>> = MutableLiveData()
	val nextTask: MutableLiveData<Any> = MutableLiveData()
	val selectCuurentEstimate: MutableLiveData<Float> = MutableLiveData()

	var room: Room? = null
		set(value) {
			if (field == null) {
				Observable.just(value)
						.subscribeOn(Schedulers.io())
						.flatMap {
							GetIssuesGateway(value!!.id).execute()
						}
						.distinctUntilChanged()
						.observeOn(AndroidSchedulers.mainThread())
						.subscribe {
							tasksLiveData.value = it
						}
			}
			field = value
		}

	fun sendEstimation(issueId: String, estimate: Float) {
		room?.let {
			Observable.just(it)
					.subscribeOn(Schedulers.io())
					.flatMap {
						CreateOrUpdateEstimateGateway(it.id, issueId, estimate)
								.execute()
					}
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe({
						nextTask.value = Any()
					}, {
						it.printStackTrace()
					})

		}
	}

	fun getUserEstimationForIssue(issue: Issue) {

		room?.let {
			Observable.just(it)
					.subscribeOn(Schedulers.io())
					.flatMap {
						GetCurrentUserGateway().execute()
					}
					.subscribe { currentUser ->
						Observable.just(it)
								.subscribeOn(Schedulers.io())
								.flatMap {
									GetEstimatesGateway(it.id, issue.id.toString())
											.execute()
								}
								.observeOn(AndroidSchedulers.mainThread())
								.flatMap {
									val filter = it.filter {
										it.userId == currentUser.id
									}
									if (filter.isNotEmpty()) {
										Observable.just(
												filter.map {
													it.estimate
												}.get(0))
									} else {
										Observable.empty()
									}
								}.subscribe({
							selectCuurentEstimate.value = it
						}, {
							it.printStackTrace()
						})
					}


		}
	}
}
