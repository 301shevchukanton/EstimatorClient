package estimator.kissteam.com.estimatorclient.retrofit

import android.util.Base64
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route


/**
 * Created by: anna
 * Date: 3/16/18.
 */
class Authenticator(private val token: String) : Authenticator {

    companion object {
        private const val RETRY_COUNT = 1
    }

    override fun authenticate(route: Route?, response: Response?): Request? {
        try {
//            HiDriveGatewaySettings
//                    .getInstance()
//                    .tokenManager
//                    .refreshToken()
        } catch (exception: RuntimeException) {
            if (response == null || responseCount(response) >= RETRY_COUNT) {
                return null;
            }
        }
        val accessToken = "Bearer ${encodeWithBase64(token)}"
        return response?.request()?.newBuilder()?.header("Authorization", accessToken)?.build()
    }

    private fun encodeWithBase64(input: String): String {
        return Base64.encodeToString(input.toByteArray(), Base64.NO_WRAP)
    }

    private fun responseCount(resp: Response): Int {
        var response = resp
        var result = 1
        while (response.priorResponse() != null) {
            response = response.priorResponse() as Response
            result++
        }
        return result
    }
}
