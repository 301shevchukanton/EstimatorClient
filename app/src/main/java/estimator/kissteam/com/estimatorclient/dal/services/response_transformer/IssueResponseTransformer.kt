package estimator.kissteam.com.estimatorclient.dal.services.response_transformer

import estimator.kissteam.com.estimatorclient.dal.entities.Issue
import estimator.kissteam.com.estimatorclient.dal.exceptions.EntityResponseTransformationError
import estimator.kissteam.com.estimatorclient.dal.services.request_response.IssueResponse
import estimator.kissteam.com.estimatorclient.retrofit.transformation.ResponseTransformer

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class IssueResponseTransformer : ResponseTransformer<IssueResponse, Issue> {

	override fun transform(response: IssueResponse): Issue {
		if (response.id != null && response.description != null && response.title != null) {
			return Issue(response.id, response.title, response.description, response.estimate)
		} else {
			throw EntityResponseTransformationError()
		}
	}
}