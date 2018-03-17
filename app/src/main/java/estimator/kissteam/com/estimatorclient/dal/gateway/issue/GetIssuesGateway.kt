package estimator.kissteam.com.estimatorclient.dal.gateway.issue

import estimator.kissteam.com.estimatorclient.dal.entities.Issue
import estimator.kissteam.com.estimatorclient.dal.services.IssueService
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class GetIssuesGateway(private val roomId: String) {

	fun execute(): Observable<List<Issue>> =
			RetrofitFactory
					.createService<IssueService>()
					.getIssues(roomId)
}