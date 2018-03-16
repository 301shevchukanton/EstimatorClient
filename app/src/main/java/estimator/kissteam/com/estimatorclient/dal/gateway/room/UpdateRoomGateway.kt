package estimator.kissteam.com.estimatorclient.dal.gateway.room

import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.dal.services.RoomService
import estimator.kissteam.com.estimatorclient.dal.services.request_entity.UpdateRoom
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class UpdateRoomGateway(private val roomId: String,
						private val id: String,
						private val strategy: String,
						private val title: String) {

	fun execute(): Observable<Room> =
			RetrofitFactory
					.createService<RoomService>()
					.updateRoom(roomId, UpdateRoom(id, title, strategy))
}