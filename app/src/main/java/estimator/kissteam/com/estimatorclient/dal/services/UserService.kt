package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.entity_responses.UserResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface UserService {

	@POST("/users")
	fun createUser(@Query("username") username: String,
				   @Query("password") password: String): Observable<UserResponse>

	@GET("/users")
	fun getUsers(): Observable<List<UserResponse>>

	//TODO question
	@GET("/user")
	fun getUser(): Observable<UserResponse>
}