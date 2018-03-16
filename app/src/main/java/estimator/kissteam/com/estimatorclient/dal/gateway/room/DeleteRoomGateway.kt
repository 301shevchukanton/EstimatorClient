package estimator.kissteam.com.estimatorclient.dal.gateway.room

import estimator.kissteam.com.estimatorclient.dal.services.RoomService
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Completable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class DeleteRoomGateway(private val roomId: String) {

	fun execute(): Completable =
			RetrofitFactory
					.createService<RoomService>()
					.deleteRoom(roomId)
}