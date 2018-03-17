package estimator.kissteam.com.estimatorclient.retrofit

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by: anna
 * Date: 3/16/18.
 */
class AuthInterceptor(private val token: String) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val accessToken = "Bearer $token"
		val requestBuilder = chain
				.request()
				.newBuilder()
				.header("Authorization", accessToken)

		val request = requestBuilder.build()
		return chain.proceed(request)
	}
}
