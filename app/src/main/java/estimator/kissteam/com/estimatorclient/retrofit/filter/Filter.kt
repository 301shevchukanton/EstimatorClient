package estimator.kissteam.com.estimatorclient.retrofit.filter


/**
 * Created by Dima Muravyov on 17.03.2018.
 */
interface Filter<Item> {

	fun filter(vararg items: Item?): Boolean
}