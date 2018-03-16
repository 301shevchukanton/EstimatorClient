package estimator.kissteam.com.estimatorclient.dal.services

import estimator.kissteam.com.estimatorclient.dal.entities.Issue
import estimator.kissteam.com.estimatorclient.dal.services.request_entity.IssueInformation
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.*


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
interface IssueService {

	@POST("/room/{roomId}/issues")
	fun createIssue(@Path("roomId") roomId: String,
					@Body issueInformation: IssueInformation): Observable<Issue>

	@GET("/room/{roomId}/issues")
	fun getIssues(@Path("roomId") roomId: String): Observable<List<Issue>>

	@GET("/room/{roomId}/issues/{issueId}")
	fun getIssue(@Path("roomId") roomId: String,
				 @Path("issueId") issueId: String): Observable<Issue>

	//TODO room owner has request with additional filed - estimation
	@PUT("/room/{roomId}/issues/{issueId}")
	fun updateIssue(@Path("roomId") roomId: String,
					@Path("issueId") issueId: String,
					@Body issueInformation: IssueInformation): Observable<Issue>

	@DELETE("/room/{roomId}/issues/{issueId}")
	fun deleteIssue(@Path("roomId") roomId: String,
					@Path("issueId") issueId: String): Completable
}