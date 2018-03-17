package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.RoomUserRequestBundle
import estimator.kissteam.com.estimatorclient.dal.services.request_response.UserResponse
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface RoomUsersService {

	@GET("rooms/{roomId}/users")
	fun getRoomUsers(@Path("roomId") roomId: String): Observable<List<UserResponse>>

	@POST("rooms/{roomId}/users")
	fun addUserToRoom(@Path("roomId") roomId: String,
					  @Body roomUserRequestBundle: RoomUserRequestBundle
	): Completable

	@DELETE("rooms/{roomId}/users")
	fun deleteUserFromRoom(@Path("roomId") roomId: String,
						   @Body roomUserRequestBundle: RoomUserRequestBundle
	): Observable<List<UserResponse>>
}