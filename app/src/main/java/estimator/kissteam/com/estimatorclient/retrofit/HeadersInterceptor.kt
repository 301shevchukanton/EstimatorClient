package estimator.kissteam.com.estimatorclient.retrofit

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by: anna
 * Date: 3/16/18.
 */
class HeadersInterceptor() : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val requestBuilder = chain
				.request()
				.newBuilder()
				.header("Content-Type", "application/json")
				.header("Accept", "application/json")

		val request = requestBuilder.build()
		return chain.proceed(request)
	}
}
