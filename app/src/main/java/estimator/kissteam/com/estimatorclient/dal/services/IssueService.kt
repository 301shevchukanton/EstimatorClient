package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.IssueRequestBundle
import estimator.kissteam.com.estimatorclient.dal.services.request_response.IssueResponse
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface IssueService {

	@POST("room/{roomId}/issues")
	fun createIssue(@Path("roomId") roomId: String,
					@Body issueRequestBundle: IssueRequestBundle): Observable<IssueResponse>

	@GET("room/{roomId}/issues")
	fun getIssues(@Path("roomId") roomId: String): Observable<List<IssueResponse>>

	@GET("room/{roomId}/issues/{issueId}")
	fun getIssue(@Path("roomId") roomId: String,
				 @Path("issueId") issueId: String): Observable<IssueResponse>

	//TODO можна ставити оцінку і її змінювати поки таск ще повністю не оцінений. як тільки оцінений - 403
	//TODO room owner has request with additional filed - estimation
	@PUT("room/{roomId}/issues/{issueId}")
	fun updateIssue(@Path("roomId") roomId: String,
					@Path("issueId") issueId: String,
					@Body issueRequestBundle: IssueRequestBundle): Observable<IssueResponse>

	//видаляти оцінку також можна тільки поки таск не оцінений
	@DELETE("room/{roomId}/issues/{issueId}")
	fun deleteIssue(@Path("roomId") roomId: String,
					@Path("issueId") issueId: String): Completable
}