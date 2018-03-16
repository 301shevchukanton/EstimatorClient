package estimator.kissteam.com.estimatorclient.dal.gateway.estimation_strategy

import estimator.kissteam.com.estimatorclient.dal.entities.Strategy
import estimator.kissteam.com.estimatorclient.dal.services.StrategyService
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable


/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class GetStrategiesGateway {

	fun execute(): Observable<List<Strategy>> =
			RetrofitFactory
					.createService<StrategyService>()
					.getStrategies()
}