package estimator.kissteam.com.estimatorclient.viewmodel.room_creator

import estimator.kissteam.com.estimatorclient.dal.entities.Strategy
import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.gateway.issue.CreateIssueGateway
import estimator.kissteam.com.estimatorclient.dal.gateway.room.CreateRoomGateway
import estimator.kissteam.com.estimatorclient.dal.gateway.room_users.AddUserToRoomGateway
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.IssueRequestBundle
import estimator.kissteam.com.estimatorclient.viewmodel.CreateRoomBundle
import io.reactivex.Completable
import io.reactivex.CompletableSource


/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class RoomCreator(private val createRoomBundle: CreateRoomBundle) {

	private class NotNullCreateRoomBundle(val name: String,
										  val description: String,
										  val issueList: List<IssueRequestBundle>,
										  val estimationStrategy: Strategy,
										  val users: List<User>)

	fun create(): Completable {
		val notNullCreateRoomBundle = createNotNullCreateRoomBundle()
		return if (notNullCreateRoomBundle != null) {
			createRoom(notNullCreateRoomBundle)
		} else {
			Completable.error(CreateRoomBundleValidationError())
		}
	}

	private fun createNotNullCreateRoomBundle(): NotNullCreateRoomBundle? {
		return if (createRoomBundle.description != null
				&& createRoomBundle.estimationStrategy != null
				&& createRoomBundle.name != null
				&& createRoomBundle.issueList != null
				&& createRoomBundle.users != null) {
			NotNullCreateRoomBundle(
					createRoomBundle.name,
					createRoomBundle.description,
					createRoomBundle.issueList,
					createRoomBundle.estimationStrategy,
					createRoomBundle.users)
		} else {
			null
		}
	}

	private fun createRoom(createRoomBundle: NotNullCreateRoomBundle): Completable {
		return CreateRoomGateway(createRoomBundle.description, createRoomBundle.estimationStrategy.id)
				.execute()
				.flatMapCompletable { room ->
					val completable: MutableList<CompletableSource> = mutableListOf()
					createRoomBundle
							.users
							.forEach { user ->
								completable
										.add(AddUserToRoomGateway(room.id, user.id).execute())
							}
					createRoomBundle
							.issueList
							.forEach { issue ->
								completable
										.add(Completable.fromObservable(
												CreateIssueGateway(room.id, issue.title, issue.description).execute()))
							}
					Completable.mergeArray(*completable.toTypedArray())
				}
	}
}