package estimator.kissteam.com.estimatorclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import estimator.kissteam.com.estimatorclient.retrofit.auth.GetAccessTokenGateway
import estimator.kissteam.com.estimatorclient.retrofit.auth.OauthRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener({
            Observable
                    .just(true)
                    .subscribeOn(Schedulers.io())
                    .flatMap {
                        Observable.just(GetAccessTokenGateway(
                                (findViewById<EditText>(R.id.etName)).text.toString(),
                                (findViewById<EditText>(R.id.etPass)).text.toString()
                        ).execute())
                    }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ gatewayResult ->
                        OauthRepository().saveAccessToken(gatewayResult.result?.accessToken)
                    }, {
                        Toast
                                .makeText(LoginActivity@ this, "Error during authorization", Toast.LENGTH_LONG)
                                .show()
                    })
        })
    }
}
