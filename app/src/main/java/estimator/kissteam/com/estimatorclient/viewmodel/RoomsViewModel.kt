package estimator.kissteam.com.estimatorclient.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.dal.gateway.room.GetRoomsGateway
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by AntonShevchuk on 16.03.2018.
 */
class RoomsViewModel : ViewModel() {

	val roomLiveData: MutableLiveData<List<Room>> = MutableLiveData()

	init {
		Observable.timer(2, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.io())
				.flatMap {
					GetRoomsGateway().execute()
				}
				.distinctUntilChanged()
				.observeOn(AndroidSchedulers.mainThread())
				.onErrorReturn { emptyList() }
				.subscribe {
					roomLiveData.value = it
				}
	}


}