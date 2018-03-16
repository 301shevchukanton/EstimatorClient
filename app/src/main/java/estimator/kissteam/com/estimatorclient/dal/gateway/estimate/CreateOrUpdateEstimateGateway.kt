package estimator.kissteam.com.estimatorclient.dal.gateway.estimate

import estimator.kissteam.com.estimatorclient.dal.services.EstimateService
import estimator.kissteam.com.estimatorclient.dal.services.request_entity.EstimateInfo
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import estimator.kissteam.com.estimatorclient.retrofit.filter.NullFieldsFilter
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class CreateOrUpdateEstimateGateway(private val roomId: String,
									private val issueId: String,
									private val estimate: String) {

	fun execute(): Observable<EstimateInfo> =
			RetrofitFactory
					.createService<EstimateService>()
					.createOrUpdateEstimate(roomId, issueId, EstimateInfo(estimate))
}