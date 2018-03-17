package estimator.kissteam.com.estimatorclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import estimator.kissteam.com.estimatorclient.retrofit.GatewayHandler
import estimator.kissteam.com.estimatorclient.retrofit.GatewayResult
import estimator.kissteam.com.estimatorclient.retrofit.TokenEntity
import estimator.kissteam.com.estimatorclient.retrofit.auth.GetAccessTokenGateway
import estimator.kissteam.com.estimatorclient.retrofit.auth.OauthRepository

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //TODO: Extract to ViewModel
        findViewById<Button>(R.id.btnLogin).setOnClickListener({

            GetAccessTokenGateway(
                    (findViewById<EditText>(R.id.etName)).text.toString(),
                    (findViewById<EditText>(R.id.etPass)).text.toString()
            ).executeAsync(object : GatewayHandler<TokenEntity> {
                override fun handleResult(gatewayResult: GatewayResult<TokenEntity>) {
                    if (gatewayResult.result != null && gatewayResult.error == null) {
                        OauthRepository().saveAccessToken(gatewayResult.result.accessToken)
                        openRoomsActivity()
                    } else if (gatewayResult.error != null) {
                        showToast(gatewayResult.error.localizedMessage)
                    } else {
                        showToast("Error during authorization")
                    }
                }
            })


//            Observable
//                    .just(true)
//                    .subscribeOn(Schedulers.io())
//                    .flatMap {
//                        Observable.just(GetAccessTokenGateway(
//                                (findViewById<EditText>(R.id.etName)).text.toString(),
//                                (findViewById<EditText>(R.id.etPass)).text.toString()
//                        ).execute())
//                    }
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe({ gatewayResult ->
//                        if (gatewayResult.result != null) {
//                            OauthRepository().saveAccessToken(gatewayResult.result.accessToken)
//                            LoginActivity@ this.startActivity(RoomsActivity.createIntent(LoginActivity@ this))
//                        } else {
//                            showToast("Invalid password")
//                        }
//
//                    }, { showToast("Error during authorization") })
        })

        findViewById<Button>(R.id.btnRegister).setOnClickListener({
            openRegisterActivity()
        })
    }

    private fun openRoomsActivity() {
        startActivity(RoomsActivity.createIntent(this))
    }

    private fun openRegisterActivity() {
        startActivity(RegisterActivity.createIntent(this))
    }

    private fun showToast(message: String) {
        Toast
                .makeText(LoginActivity@ this, message, Toast.LENGTH_LONG)
                .show()
    }
}
