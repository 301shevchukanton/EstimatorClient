package estimator.kissteam.com.estimatorclient.dal.entities


/**
 * Created by Dima Muravyov on 16.03.2018.
 */
//TODO need some clarifications, change strategy and crateo from string to Object
class Room(
		val id: String,
		val title: String,
		val strategy: String,
		val creator: String)