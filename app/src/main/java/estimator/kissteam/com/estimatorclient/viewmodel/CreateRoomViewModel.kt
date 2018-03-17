package estimator.kissteam.com.estimatorclient.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import estimator.kissteam.com.estimatorclient.viewmodel.room_creator.RoomCreator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by AntonShevchuk on 16.03.2018.
 */
class CreateRoomViewModel : ViewModel() {
	var createRoomBundle: CreateRoomBundle? = null
	var indexOfCurrentView: Int = 0
		get() = field
		set(value) {
			field = value
		}
	val roomCreated = MutableLiveData<Any>()
	val roomCreationError = MutableLiveData<Throwable>()

	fun createRoom() {
		createRoomBundle?.let {
			RoomCreator(it)
					.create()
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe({
						roomCreated.value = Any()
					}, { error ->
						roomCreationError.value = error
					})
		}

	}
}