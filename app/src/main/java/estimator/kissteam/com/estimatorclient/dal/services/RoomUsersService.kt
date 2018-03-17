package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.RoomUserRequestBundle
import estimator.kissteam.com.estimatorclient.dal.services.request_response.UserResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface RoomUsersService {

	@POST("rooms/{roomId}/users")
	fun addUserToRoom(@Path("roomId") roomId: String,
					  @Body roomUserRequestBundle: RoomUserRequestBundle): Observable<List<UserResponse>>

	@DELETE("rooms/{roomId}/users")
	fun deleteUserFromRoom(@Path("roomId") roomId: String,
						   @Body roomUserRequestBundle: RoomUserRequestBundle): Observable<List<UserResponse>>
}