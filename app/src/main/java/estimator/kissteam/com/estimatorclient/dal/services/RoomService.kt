package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.RoomRequestBundle
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.UpdateRoomRequestBundle
import estimator.kissteam.com.estimatorclient.dal.services.request_response.RoomResponse
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface RoomService {

	@POST("rooms")
	fun createRoom(@Body roomRequestBundle: RoomRequestBundle): Observable<RoomResponse>

	@GET("rooms")
	fun getRooms(): Observable<List<RoomResponse>>

	@GET("rooms/{roomId}")
	fun getRoom(@Path("roomId") roomId: String): Observable<RoomResponse>

	@PUT("rooms/{roomId}")
	fun updateRoom(@Path("roomId") roomId: String,
				   @Body updateRoomRequestBundle: UpdateRoomRequestBundle): Observable<RoomResponse>

	@DELETE("rooms/{roomId}")
	fun deleteRoom(@Path("roomId") roomId: String): Completable
}