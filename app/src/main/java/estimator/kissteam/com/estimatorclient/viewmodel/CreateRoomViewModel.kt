package estimator.kissteam.com.estimatorclient.viewmodel

import android.arch.lifecycle.ViewModel
import estimator.kissteam.com.estimatorclient.dal.entity.EstimationStrategy
import estimator.kissteam.com.estimatorclient.dal.entity.Issue

/**
 * Created by AntonShevchuk on 16.03.2018.
 */
public class CreateRoomViewModel : ViewModel() {



	class CreateRoomBundle(val name: String,
	                       val description: String,
	                       val taskList: List<Issue>,
	                       val estimationStrategy : EstimationStrategy,
	                       val users: List<User>)

	fun setDescription(description: String) {

	}

}