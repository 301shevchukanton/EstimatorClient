package estimator.kissteam.com.estimatorclient.dal.gateway.estimate

import estimator.kissteam.com.estimatorclient.dal.entities.Estimate
import estimator.kissteam.com.estimatorclient.dal.services.EstimateService
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable


/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class GetEstimatesGateway(private val roomId: String,
						  private val issueId: String) {

	fun execute(): Observable<List<Estimate>> =
			RetrofitFactory
					.createService<EstimateService>()
					.getEstimates(roomId, issueId)
}