package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.services.request_entity.RoomUser
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface RoomUsersService {

	@POST("/rooms/{roomId}/users")
	fun addUserToRoom(@Path("roomId") roomId: String,
					  @Body roomUser: RoomUser): Observable<List<User>>

	@DELETE("/rooms/{roomId}/users")
	fun deleteUserFromRoom(@Path("roomId") roomId: String,
						   @Body roomUser: RoomUser): Observable<List<User>>
}