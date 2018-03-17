package estimator.kissteam.com.estimatorclient.viewmodel

import android.arch.lifecycle.ViewModel

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

	fun createRoom() {
		
	}

}