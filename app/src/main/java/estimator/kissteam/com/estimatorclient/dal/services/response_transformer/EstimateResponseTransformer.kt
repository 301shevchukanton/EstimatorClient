package estimator.kissteam.com.estimatorclient.dal.services.response_transformer

import estimator.kissteam.com.estimatorclient.dal.entities.Estimate
import estimator.kissteam.com.estimatorclient.dal.exceptions.EntityResponseTransformationError
import estimator.kissteam.com.estimatorclient.dal.services.request_response.EstimateResponse
import estimator.kissteam.com.estimatorclient.retrofit.transformation.ResponseTransformer


/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class EstimateResponseTransformer : ResponseTransformer<EstimateResponse, Estimate> {

	override fun transform(response: EstimateResponse): Estimate {
		if (response.estimate != null && response.user_id != null) {
			return Estimate(response.user_id, response.estimate)
		} else {
			throw EntityResponseTransformationError()
		}
	}
}