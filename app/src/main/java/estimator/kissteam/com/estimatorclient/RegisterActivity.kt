package estimator.kissteam.com.estimatorclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import estimator.kissteam.com.estimatorclient.dal.gateway.user.CreateUserGateway
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by: anna
 * Date: 3/17/18.
 */
class RegisterActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context) =
                Intent(context, RegisterActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        findViewById<Button>(R.id.btnRegister).setOnClickListener({
            val user = (findViewById<EditText>(R.id.etName)).text.toString()
            val pass = (findViewById<EditText>(R.id.etPass)).text.toString()
            CreateUserGateway(user, pass)
                    .execute()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        showToast("${user} has been created successfully!")
                        finish()
                    }, {
                        showToast("Can't create user. Something went wrong")
                    })
        })
    }

    private fun showToast(message: String) {
        Toast
                .makeText(LoginActivity@ this, message, Toast.LENGTH_LONG)
                .show()
    }
}