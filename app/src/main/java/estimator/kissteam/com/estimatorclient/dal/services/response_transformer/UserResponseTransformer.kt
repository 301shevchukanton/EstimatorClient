package estimator.kissteam.com.estimatorclient.dal.services.response_transformer

import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.exceptions.EntityResponseTransformationError
import estimator.kissteam.com.estimatorclient.dal.services.request_response.UserResponse
import estimator.kissteam.com.estimatorclient.retrofit.transformation.ResponseTransformer

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class UserResponseTransformer : ResponseTransformer<UserResponse, User> {

	override fun transform(response: UserResponse): User {
		if (response.id != null
				&& response.email != null
				&& response.created_at != null
				&& response.updated_at != null) {
			return User(response.id, response.email, response.created_at, response.updated_at)
		} else {
			throw EntityResponseTransformationError()
		}
	}
}