package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.NewUserRequestBundle
import estimator.kissteam.com.estimatorclient.dal.services.request_response.UserResponse
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface UsersService {

	@POST("users")
	fun createUser(@Body newUserRequestBundle: NewUserRequestBundle): Observable<UserResponse>

	@GET("users")
	fun getUsers(): Observable<List<UserResponse>>

	@GET("user")
	fun getUser(): Observable<UserResponse>
}