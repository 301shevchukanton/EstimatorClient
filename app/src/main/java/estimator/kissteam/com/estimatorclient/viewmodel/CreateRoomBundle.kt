package estimator.kissteam.com.estimatorclient.viewmodel;
import estimator.kissteam.com.estimatorclient.dal.entities.EstimationStrategy
import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.services.request_entity.IssueInformation

data class CreateRoomBundle(val name: String? = null,
                            val description: String? = null,
                            val issueList: MutableList<IssueInformation>? = mutableListOf(),
                            val estimationStrategy: EstimationStrategy? = null,
                            val users: MutableList<User>? = mutableListOf())