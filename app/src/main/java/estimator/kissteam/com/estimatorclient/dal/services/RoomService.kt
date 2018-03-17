package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.RoomInfoRequestBundle
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.UpdateRoomRequestBundle
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface RoomService {

	@POST("rooms")
	fun createRoom(@Body roomInfoRequestBundle: RoomInfoRequestBundle): Observable<Room>

	@GET("rooms")
	fun getRooms(): Observable<List<Room>>

	@GET("rooms/{roomId}")
	fun getRoom(@Path("roomId") roomId: String): Observable<Room>

	@PUT("rooms/{roomId}")
	fun updateRoom(@Path("roomId") roomId: String,
				   @Body updateRoomRequestBundle: UpdateRoomRequestBundle): Observable<Room>

	@DELETE("rooms/{roomId}")
	fun deleteRoom(@Path("roomId") roomId: String): Completable
}