package estimator.kissteam.com.estimatorclient.retrofit.filter


/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class NullFieldsFilter : Filter<Any> {

	override fun filter(vararg items: Any?): Boolean {
		items.forEach {
			if (it == null) return false
		}
		return true
	}
}