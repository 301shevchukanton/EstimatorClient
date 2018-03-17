package estimator.kissteam.com.estimatorclient.retrofit.filter

import android.os.Bundle
import org.junit.Assert
import org.junit.Test

/**
 * Created by Dima Muravyov on 17.03.2018.
 */
class NullFieldsFilterTest {

	@Test
	fun testSuccessWhenAllFieldsNotNulls() {
		val filter: Filter<Any> = NullFieldsFilter()

		val bundle: Bundle? = Bundle()

		Assert.assertTrue(filter.filter(1, 1L, "sdfsdf", false, bundle))
	}

	@Test
	fun testFailWhenObjectIsNull() {
		val filter: Filter<Any> = NullFieldsFilter()

		val bundle: Bundle? = null

		Assert.assertFalse(filter.filter(1, 1L, "sdfsdf", false, bundle))
	}

	@Test
	fun testFailWhenPrimitiveIsNull() {
		val filter: Filter<Any> = NullFieldsFilter()

		val bundle: Bundle? = Bundle()

		Assert.assertFalse(filter.filter(1, null, "sdfsdf", false, bundle))
	}
}