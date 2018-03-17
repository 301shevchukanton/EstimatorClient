package estimator.kissteam.com.estimatorclient.dal.gateway.issue

import estimator.kissteam.com.estimatorclient.dal.services.IssueService
import estimator.kissteam.com.estimatorclient.retrofit.RetrofitFactory
import io.reactivex.Completable

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class DeleteIssueGateway(private val roomId: String,
						 private val issueId: String) {

	fun execute(): Completable =
			RetrofitFactory
					.createService<IssueService>()
					.deleteIssue(roomId, issueId)
}