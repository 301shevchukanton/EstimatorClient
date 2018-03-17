package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.entities.Estimate
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.EstimateInfoRequestBundle
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface EstimateService {

	@GET("room/{roomId}/issues/{issueId}/estimates")
	fun getEstimates(@Path("roomId") roomId: String,
					 @Path("issueId") issueId: String): Observable<List<Estimate>>

	@PUT("room/{roomId}/issues/{issueId}/estimates")
	fun createOrUpdateEstimate(@Path("roomId") roomId: String,
							   @Path("issueId") issueId: String,
							   @Body estimateInfoRequestBundle: EstimateInfoRequestBundle): Observable<EstimateInfoRequestBundle>

	@DELETE("room/{roomId}/issues/{issueId}/estimates")
	fun deleteEstimate(@Path("roomId") roomId: String,
					   @Path("issueId") issueId: String): Completable
}