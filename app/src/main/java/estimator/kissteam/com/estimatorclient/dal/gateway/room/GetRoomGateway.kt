package estimator.kissteam.com.estimatorclient.dal.gateway.room

import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.dal.services.RoomService
import estimator.kissteam.com.estimatorclient.dal.services.response_transformer.RoomResponseTransformer
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class GetRoomGateway(private val roomId: String) {

	fun execute(): Observable<Room> =
			RetrofitFactory
					.createService<RoomService>()
					.getRoom(roomId)
					.map { RoomResponseTransformer().transform(it) }
}