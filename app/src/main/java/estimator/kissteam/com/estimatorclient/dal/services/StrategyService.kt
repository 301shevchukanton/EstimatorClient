package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.services.request_response.StrategyResponse
import io.reactivex.Observable
import retrofit2.http.GET


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface StrategyService {

	@GET("strategies")
	fun getStrategies(): Observable<List<StrategyResponse>>
}