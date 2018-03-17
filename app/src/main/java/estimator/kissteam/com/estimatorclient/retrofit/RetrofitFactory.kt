package estimator.kissteam.com.estimatorclient.retrofit

import estimator.kissteam.com.estimatorclient.retrofit.auth.AccessTokenProvider
import estimator.kissteam.com.estimatorclient.retrofit.auth.AccessTokenProviderImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by: anna
 * Date: 3/16/18.
 */
class RetrofitFactory constructor(private val baseUrl: String = "http://159.89.7.3/api/") {

	companion object {
		private const val MAX_REQUESTS_PER_HOST = 5
		private const val CONNECT_TIMEOUT = 20L

		inline fun <reified Service> createService(): Service =
				RetrofitFactory()
						.create()
						.create(Service::class.java)

		inline fun <reified Service> createServiceForAuth(): Service =
				RetrofitFactory()
						.createForAuth()
						.create(Service::class.java)
	}

	fun create(tokenProvider: AccessTokenProvider = AccessTokenProviderImpl()): Retrofit {
		val interceptor = HttpLoggingInterceptor()
				.setLevel(HttpLoggingInterceptor.Level.BODY)

		val client = OkHttpClient.Builder()
				.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
				.addNetworkInterceptor(interceptor)
				.addInterceptor(AuthInterceptor(tokenProvider.provide()))
				.addInterceptor(HeadersInterceptor())
				.build()

		client.dispatcher().maxRequestsPerHost = MAX_REQUESTS_PER_HOST

		return Retrofit.Builder()
				.baseUrl(this.baseUrl)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build()
	}

	fun createForAuth(): Retrofit {
		val interceptor = HttpLoggingInterceptor()
				.setLevel(HttpLoggingInterceptor.Level.BODY)

		val client = OkHttpClient.Builder()
				.addNetworkInterceptor(interceptor)
				.addInterceptor(HeadersInterceptor())
				.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
				.build()

		return Retrofit.Builder()
				.baseUrl(baseUrl)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build()
	}
}
