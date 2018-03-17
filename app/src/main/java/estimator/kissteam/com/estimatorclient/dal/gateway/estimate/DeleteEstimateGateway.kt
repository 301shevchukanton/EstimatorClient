package estimator.kissteam.com.estimatorclient.dal.gateway.estimate

import estimator.kissteam.com.estimatorclient.dal.services.EstimateService
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Completable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class DeleteEstimateGateway(private val roomId: String,
							private val issueId: String) {

	fun execute(): Completable =
			RetrofitFactory
					.createService<EstimateService>()
					.deleteEstimate(roomId, issueId)
}