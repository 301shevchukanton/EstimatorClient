package estimator.kissteam.com.estimatorclient

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import estimator.kissteam.com.estimatorclient.viewmodel.CreateRoomViewModel

class CreateRoomActivity : AppCompatActivity() {

	private val viewModelClass = CreateRoomViewModel::class.java


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_create_room)
	}

	private fun createRoomViewModel() = ViewModelProviders.of(this).get(viewModelClass)

}
