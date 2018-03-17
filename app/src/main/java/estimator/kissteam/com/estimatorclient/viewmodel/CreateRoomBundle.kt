package estimator.kissteam.com.estimatorclient.viewmodel;
import estimator.kissteam.com.estimatorclient.dal.entities.Strategy
import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.IssueRequestBundle

data class CreateRoomBundle(val name: String? = null,
                            val description: String? = null,
                            val issueList: MutableList<IssueRequestBundle>? = mutableListOf(),
                            val estimationStrategy: Strategy? = null,
                            val users: MutableList<User>? = mutableListOf())