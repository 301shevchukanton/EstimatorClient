package estimator.kissteam.com.estimatorclient.dal.gateway.estimate

import estimator.kissteam.com.estimatorclient.dal.entities.Estimate
import estimator.kissteam.com.estimatorclient.dal.services.EstimateService
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.EstimateRequestBundle
import estimator.kissteam.com.estimatorclient.dal.services.response_transformer.EstimateResponseTransformer
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class CreateOrUpdateEstimateGateway(private val roomId: String,
									private val issueId: String,
									private val estimate: Float) {

	fun execute(): Observable<Estimate> =
			RetrofitFactory
					.createService<EstimateService>()
					.createOrUpdateEstimate(roomId, issueId, EstimateRequestBundle(estimate))
					.map { EstimateResponseTransformer().transform(it) }
}