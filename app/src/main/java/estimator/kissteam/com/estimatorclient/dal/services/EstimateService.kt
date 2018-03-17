package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.EstimateRequestBundle
import estimator.kissteam.com.estimatorclient.dal.services.request_response.EstimateResponse
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface EstimateService {

	@GET("room/{roomId}/issues/{issueId}/estimates")
	fun getEstimates(@Path("roomId") roomId: String,
					 @Path("issueId") issueId: String): Observable<List<EstimateResponse>>

	@PUT("room/{roomId}/issues/{issueId}/estimates")
	fun createOrUpdateEstimate(@Path("roomId") roomId: String,
							   @Path("issueId") issueId: String,
							   @Body estimateRequestBundle: EstimateRequestBundle
	): Observable<EstimateResponse>

	@DELETE("room/{roomId}/issues/{issueId}/estimates")
	fun deleteEstimate(@Path("roomId") roomId: String,
					   @Path("issueId") issueId: String): Completable
}