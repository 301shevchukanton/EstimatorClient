package estimator.kissteam.com.estimatorclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import estimator.kissteam.com.estimatorclient.retrofit.auth.GetAccessTokenGateway
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button).setOnClickListener({
           val i = 1
            Observable
                    .just(true)
                    .subscribeOn(Schedulers.io())
                    .flatMap {
                        Observable.just(GetAccessTokenGateway().execute())
                    }
                    //.map { result-> result.result }
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
                    //.onErrorReturn { error -> System.out.println(error) }
        })
    }
}
