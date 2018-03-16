package estimator.kissteam.com.estimatorclient.retrofit.auth

import android.content.SharedPreferences
import android.preference.PreferenceManager
import estimator.kissteam.com.estimatorclient.App


/**
 * Created by: anna
 * Date: 3/16/18.
 */
class OauthRepository {

    companion object {
        val preference: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.INSTANCE)
    }

    fun getAccessToken() = preference.getString("preference_access_token", "")

}