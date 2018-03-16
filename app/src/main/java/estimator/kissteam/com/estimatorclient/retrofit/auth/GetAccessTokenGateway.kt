package estimator.kissteam.com.estimatorclient.retrofit.auth

import estimator.kissteam.com.estimatorclient.retrofit.*
import io.reactivex.Observable
import java.io.IOException

/**
 * Created by: anna
 * Date: 3/16/18.
 */
class GetAccessTokenGateway constructor(
        private val baseUrl: String = "http://159.89.7.3",
        private val responseTransformer: ResponseTransformer<GetAccessTokenResponse, TokenEntity> = GetAccessTokenResponseTransformer())
    : Gateway<TokenEntity> {

    override fun execute(): GatewayResult<TokenEntity> {
        return createRequest()
                .map { GatewayResult(responseTransformer.transform(it)) }
                .onErrorReturn { error ->
                    GatewayResult(IOException(error))
                }
                .blockingFirst()
    }

//    override fun executeAsync(domainGatewayHandler: DomainGatewayHandler<TokenEntity>) {
//        createRequest()
//                .map { response -> responseTransformer.transform(response) }
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { entity -> domainGatewayHandler.handleDomainGatewayResult(DomainGatewayResult(entity)) },
//                        { error ->
//                            domainGatewayHandler.handleDomainGatewayResult(
//                                    DomainGatewayResult<TokenEntity>(IOException(error.message)))
//                        })
//    }

    private fun createRequest(): Observable<GetAccessTokenResponse> =
            RetrofitFactory(baseUrl)
                    .createForAuth()
                    .create(GetAccessTokenService::class.java)
                    .oauthToken(createRequestParameters())

    private fun createRequestParameters(): Map<String, String> =
            mutableMapOf<String, String>().apply {
                put("grant_type", "password")
                put("client_id", "2")
                put("client_secret", "Vm5zJt1czIbAXQfwnwNY6kJIyl2f7HDHPq66iQCR")
                put("username", "alk@qapint.com")
                put("password", "111111")
            }
}
