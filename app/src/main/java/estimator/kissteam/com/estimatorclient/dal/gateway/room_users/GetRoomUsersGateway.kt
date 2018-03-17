package estimator.kissteam.com.estimatorclient.dal.gateway.room_users

import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.services.RoomUsersService
import estimator.kissteam.com.estimatorclient.dal.services.response_transformer.UserResponseTransformer
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class GetRoomUsersGateway(private val roomId: String) {

	fun execute(): Observable<List<User>> =
			RetrofitFactory
					.createService<RoomUsersService>()
					.getRoomUsers(roomId)
					.map { entities ->
						entities.map { UserResponseTransformer().transform(it) }
					}
}