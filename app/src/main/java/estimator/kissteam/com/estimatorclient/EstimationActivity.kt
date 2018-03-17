package estimator.kissteam.com.estimatorclient

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import estimator.kissteam.com.estimatorclient.dal.entities.Room


/**
 * Created by: anna
 * Date: 3/17/18.
 */
class EstimationActivity : AppCompatActivity() {

	companion object {
		fun createIntent(context: Context, room: Room? = null) : Intent {
			val intent = Intent(context, EstimationActivity::class.java)
			room?.let {
				intent.putExtra("ROOM", it)
			}
			return intent
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_estimation)
	}
}