package estimator.kissteam.com.estimatorclient.retrofit.auth

/**
 * Created by: anna
 * Date: 3/16/18.
 */

class AccessTokenProviderImpl : AccessTokenProvider {
    override fun provide() = OauthRepository().getAccessToken()
}