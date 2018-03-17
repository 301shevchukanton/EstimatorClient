package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.NewUserInfoRequestBundle
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface UsersService {

	@POST("users")
	fun createUser(@Body newUserInfoRequestBundle: NewUserInfoRequestBundle): Observable<User>

	@GET("users")
	fun getUsers(): Observable<List<User>>

	@GET("user")
	fun getUser(): Observable<User>
}