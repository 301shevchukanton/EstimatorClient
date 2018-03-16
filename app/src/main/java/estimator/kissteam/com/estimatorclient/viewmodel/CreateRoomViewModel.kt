package estimator.kissteam.com.estimatorclient.viewmodel

import android.arch.lifecycle.ViewModel
import estimator.kissteam.com.estimatorclient.dal.entities.EstimationStrategy
import estimator.kissteam.com.estimatorclient.dal.entities.Issue
import estimator.kissteam.com.estimatorclient.dal.entities.User

/**
 * Created by AntonShevchuk on 16.03.2018.
 */
public class CreateRoomViewModel : ViewModel() {

	val createRoomBundle: CreateRoomBundle = CreateRoomBundle()

	data class CreateRoomBundle(val name: String? = null,
	                            val description: String? = null,
	                            val issueList: MutableList<Issue>? = mutableListOf(),
	                            val estimationStrategy: EstimationStrategy? = null,
	                            val users: MutableList<User>? = mutableListOf())

	fun setName(name: String) {
		createRoomBundle.copy(name = name)
	}

	fun setDescription(description: String) {
		createRoomBundle.copy(description = description)
	}

	fun addIssue(issue: Issue) {
		createRoomBundle.issueList?.add(issue)
	}

	fun setEstimationStrategy(estimationStrategy: EstimationStrategy?) {
		createRoomBundle.copy(estimationStrategy =  estimationStrategy)
	}

	fun addUser(user: User) {
		createRoomBundle.users?.add(user)
	}

}