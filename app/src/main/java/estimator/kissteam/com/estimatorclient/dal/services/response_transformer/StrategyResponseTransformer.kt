package estimator.kissteam.com.estimatorclient.dal.services.response_transformer

import estimator.kissteam.com.estimatorclient.dal.entities.Strategy
import estimator.kissteam.com.estimatorclient.dal.exceptions.EntityResponseTransformationError
import estimator.kissteam.com.estimatorclient.dal.services.request_response.StrategyResponse
import estimator.kissteam.com.estimatorclient.retrofit.transformation.ResponseTransformer

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class StrategyResponseTransformer : ResponseTransformer<StrategyResponse, Strategy> {

	override fun transform(response: StrategyResponse): Strategy {
		if (response.id != null && response.title != null && response.description != null) {
			return Strategy(response.id, response.title, response.description)
		} else {
			throw EntityResponseTransformationError()
		}
	}
}