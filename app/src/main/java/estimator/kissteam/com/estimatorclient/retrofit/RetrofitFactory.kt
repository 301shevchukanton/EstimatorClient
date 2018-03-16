package estimator.kissteam.com.estimatorclient.retrofit

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
class RetrofitFactory @JvmOverloads constructor(private val baseUrl: String = "http://159.89.7.3/") {

    companion object {
        private const val MAX_REQUESTS_PER_HOST = 5
        private const val CONNECT_TIMEOUT = 20L
    }

    fun create(tokenProvider: AccessTokenProvider = AccessTokenProviderImpl()): Retrofit {
        val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
        //logging level 'BODY' breaking streaming gateways

        val client = OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .addNetworkInterceptor(interceptor)
                .addInterceptor(AuthInterceptor(tokenProvider.provide()))
                .authenticator(Authenticator(tokenProvider.provide()))
                .build()

        client.dispatcher().maxRequestsPerHost = MAX_REQUESTS_PER_HOST

        return Retrofit.Builder()
                .baseUrl("${this.baseUrl}/")
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
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl("${this.baseUrl}/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
    }
}
