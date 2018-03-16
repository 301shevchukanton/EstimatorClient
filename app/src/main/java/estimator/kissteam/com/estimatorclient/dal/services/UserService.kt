package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.entities.User
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
				   @Query("password") password: String): Observable<User>

	@GET("/users")
	fun getUsers(): Observable<List<User>>

	@GET("/user")
	fun getUser(): Observable<User>
}