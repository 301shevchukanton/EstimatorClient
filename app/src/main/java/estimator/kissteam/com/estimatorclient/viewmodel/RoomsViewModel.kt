package estimator.kissteam.com.estimatorclient.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.dal.gateway.room.GetRoomGateway
import estimator.kissteam.com.estimatorclient.dal.gateway.room.GetRoomsGateway
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by AntonShevchuk on 16.03.2018.
 */
class RoomsViewModel : ViewModel() {

	val liveData : MutableLiveData<List<Room>> = MutableLiveData()

	init {
		Observable.timer(5, TimeUnit.SECONDS)
				.subscribeOn(Schedulers.io())
				.flatMap {
					GetRoomsGateway().execute()
				}
				.distinctUntilChanged()
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe {
					liveData.value = it
				}
	}


}