package estimator.kissteam.com.estimatorclient.dal.gateway.estimate

import estimator.kissteam.com.estimatorclient.dal.services.EstimateService
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.EstimateInfoRequestBundle
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class CreateOrUpdateEstimateGateway(private val roomId: String,
									private val issueId: String,
									private val estimate: String) {

	fun execute(): Observable<EstimateInfoRequestBundle> =
			RetrofitFactory
					.createService<EstimateService>()
					.createOrUpdateEstimate(roomId, issueId, EstimateInfoRequestBundle(estimate))
}