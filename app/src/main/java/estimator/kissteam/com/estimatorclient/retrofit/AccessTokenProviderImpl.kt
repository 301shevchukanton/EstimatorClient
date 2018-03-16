package estimator.kissteam.com.estimatorclient.retrofit

/**
 * Created by: anna
 * Date: 3/16/18.
 */

class AccessTokenProviderImpl : AccessTokenProvider {
    override fun provide() = OauthRepository().getAccessToken()
}