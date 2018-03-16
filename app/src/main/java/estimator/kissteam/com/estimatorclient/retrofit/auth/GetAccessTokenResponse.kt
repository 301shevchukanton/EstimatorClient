package estimator.kissteam.com.estimatorclient.retrofit.auth

/**
 * Created by: anna
 * Date: 3/16/18.
 */
data class GetAccessTokenResponse(val access_token: String,
                                  val expires_in: Int,
                                  val token_type: String)
