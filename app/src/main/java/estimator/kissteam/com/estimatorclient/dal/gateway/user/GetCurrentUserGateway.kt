package estimator.kissteam.com.estimatorclient.dal.gateway.user

import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.services.UsersService
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class GetCurrentUserGateway {

	fun execute(): Observable<User> =
			RetrofitFactory
					.createService<UsersService>()
					.getUser()
}