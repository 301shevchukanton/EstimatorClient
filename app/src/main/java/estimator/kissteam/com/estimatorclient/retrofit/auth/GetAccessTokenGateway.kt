package estimator.kissteam.com.estimatorclient.retrofit.auth

import estimator.kissteam.com.estimatorclient.retrofit.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

/**
 * Created by: anna
 * Date: 3/16/18.
 */
class GetAccessTokenGateway constructor(
        private val user: String,
        private val password: String,
        private val responseTransformer: ResponseTransformer<GetAccessTokenResponse, TokenEntity> = GetAccessTokenResponseTransformer())
    : Gateway<TokenEntity> {

//    override fun execute(): GatewayResult<TokenEntity> {
//        return createRequest()
//                .map { GatewayResult(responseTransformer.transform(it)) }
//                .onErrorReturn { error ->
//                    GatewayResult(IOException(error))
//                }
//                .blockingFirst()
//    }

    override fun executeAsync(gatewayHandler: GatewayHandler<TokenEntity>) {
        createRequest()
                .map { response -> responseTransformer.transform(response) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { entity ->
                            gatewayHandler.handleResult(GatewayResult(entity))
                        },
                        { error ->
                            gatewayHandler.handleResult(
                                    GatewayResult<TokenEntity>(IOException(error.message)))
                        })
    }

    private fun createRequest(): Observable<GetAccessTokenResponse> =
            RetrofitFactory()
                    .createForAuth()
                    .create(GetAccessTokenService::class.java)
                    .oauthToken(createRequestParameters())

    private fun createRequestParameters(): Map<String, String> =
            mutableMapOf<String, String>().apply {
                put("grant_type", "password")
                put("client_id", "2")
                put("client_secret", "Vm5zJt1czIbAXQfwnwNY6kJIyl2f7HDHPq66iQCR")
                put("email", user)
                put("password", password)
            }
}
