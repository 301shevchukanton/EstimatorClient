package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.services.request_entity.Username
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface UsersService {

	@POST("/users")
	fun createUser(@Query("username") username: String,
				   @Query("password") password: String): Observable<User>

	@GET("/users")
	fun getUsers(): Observable<List<User>>

	/*
	Get current user
	 */
	@GET("/user")
	fun getUser(): Observable<User>

	/*
	Update current user
	 */
	@PUT("/users")
	fun updateUser(@Body username: Username): Observable<User>
}