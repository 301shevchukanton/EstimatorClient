package estimator.kissteam.com.estimatorclient.dal.gateway.room_users

import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.services.RoomUsersService
import estimator.kissteam.com.estimatorclient.dal.services.request_entity.RoomUser
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable


/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class AddUserToRoomGateway(private val roomId: String,
						   private val userId: String) {

	fun execute(): Observable<List<User>> =
			RetrofitFactory
					.createService<RoomUsersService>()
					.addUserToRoom(roomId, RoomUser(userId))
}