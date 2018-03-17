package estimator.kissteam.com.estimatorclient.dal.gateway.strategy

import estimator.kissteam.com.estimatorclient.dal.entities.Strategy
import estimator.kissteam.com.estimatorclient.dal.services.StrategyService
import estimator.kissteam.com.estimatorclient.dal.services.response_transformer.StrategyResponseTransformer
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
					.map { entities ->
						entities.map { StrategyResponseTransformer().transform(it) }
					}
}