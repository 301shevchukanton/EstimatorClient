package estimator.kissteam.com.estimatorclient.dal.gateway.issue

import estimator.kissteam.com.estimatorclient.dal.entities.Issue
import estimator.kissteam.com.estimatorclient.dal.services.IssueService
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.IssueRequestBundle
import estimator.kissteam.com.estimatorclient.dal.services.response_transformer.IssueResponseTransformer
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Observable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class UpdateIssueGateway(private val roomId: String,
						 private val issueId: String,
						 private val title: String,
						 private val description: String) {

	fun execute(): Observable<Issue> =
			RetrofitFactory
					.createService<IssueService>()
					.updateIssue(roomId, issueId, IssueRequestBundle(title, description))
					.map { IssueResponseTransformer().transform(it) }
}