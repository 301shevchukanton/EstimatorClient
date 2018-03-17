package estimator.kissteam.com.estimatorclient.dal.gateway.room_users

import estimator.kissteam.com.estimatorclient.dal.services.RoomUsersService
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.RoomUserRequestBundle
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Completable


/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class AddUserToRoomGateway(private val roomId: String,
						   private val userId: String) {

	fun execute(): Completable =
			RetrofitFactory
					.createService<RoomUsersService>()
					.addUserToRoom(roomId, RoomUserRequestBundle(userId))
}