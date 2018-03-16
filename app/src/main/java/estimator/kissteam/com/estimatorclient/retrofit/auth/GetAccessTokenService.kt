package estimator.kissteam.com.estimatorclient.retrofit.auth

import io.reactivex.Observable
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by: anna
 * Date: 3/16/18.
 */
interface GetAccessTokenService {

    @FormUrlEncoded
    @POST("/oauth/token")
    fun oauthToken(@FieldMap params: Map<String, String>)
            : Observable<GetAccessTokenResponse>
}