package estimator.kissteam.com.estimatorclient.dal.services.response_transformer

import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.dal.exceptions.EntityResponseTransformationError
import estimator.kissteam.com.estimatorclient.dal.services.request_response.RoomResponse
import estimator.kissteam.com.estimatorclient.retrofit.transformation.ResponseTransformer

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class RoomResponseTransformer : ResponseTransformer<RoomResponse, Room> {

	override fun transform(response: RoomResponse): Room {
		if (response.id != null
				&& response.creator != null
				&& response.title != null
				&& response.strategy != null) {
			return Room(response.id, response.title, response.strategy, response.creator)
		} else {
			throw EntityResponseTransformationError()
		}
	}
}