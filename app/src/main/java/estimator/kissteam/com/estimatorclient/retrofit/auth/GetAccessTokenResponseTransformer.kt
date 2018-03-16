package estimator.kissteam.com.estimatorclient.retrofit.auth

import estimator.kissteam.com.estimatorclient.retrofit.transformation.NormalizationStrategy
import estimator.kissteam.com.estimatorclient.retrofit.transformation.NormalizationStrategyImpl
import estimator.kissteam.com.estimatorclient.retrofit.transformation.ResponseTransformer
import estimator.kissteam.com.estimatorclient.retrofit.TokenEntity

/**
 * Created by: anna
 * Date: 3/16/18.
 */
class GetAccessTokenResponseTransformer(private val normalizationStrategy: NormalizationStrategy = NormalizationStrategyImpl())
    : ResponseTransformer<GetAccessTokenResponse, TokenEntity> {

    override fun transform(response: GetAccessTokenResponse): TokenEntity =
            normalizationStrategy.run {
                return@run TokenEntity(
                        normalize(response.access_token))
            }
}
