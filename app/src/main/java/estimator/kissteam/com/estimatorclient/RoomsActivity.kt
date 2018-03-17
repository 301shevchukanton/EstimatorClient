package estimator.kissteam.com.estimatorclient

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.view.recycler.RoomsRecyclerViewAdapter
import estimator.kissteam.com.estimatorclient.viewmodel.RoomsViewModel
import kotlinx.android.synthetic.main.activity_rooms.*


/**
 * Created by: anna
 * Date: 3/17/18.
 */
class RoomsActivity : AppCompatActivity() {

	private val viewModelClass = RoomsViewModel::class.java

	private val recyclerViewAdapter = RoomsRecyclerViewAdapter(mutableListOf())

	private fun getRoomsViewModel() = ViewModelProviders
			.of(this)
			.get(viewModelClass)

	companion object {
		fun createIntent(context: Context) =
				Intent(context, RoomsActivity::class.java)
						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_rooms)
		this.recyclerRooms.layoutManager = LinearLayoutManager(this)
		this.recyclerRooms.adapter = this.recyclerViewAdapter

		btnCreateRoom.setOnClickListener {
			startActivity(Intent(this, CreateRoomActivity::class.java))
		}
		getRoomsViewModel()
				.liveData
				.observe(this, Observer {
					if (it != null) {
						this.recyclerViewAdapter.myDataset.clear()
						this.recyclerViewAdapter.myDataset.addAll<Room>(it)
						this.recyclerViewAdapter.notifyDataSetChanged()
					}
				})
		recyclerViewAdapter
				.clickedRoom
				.observe(this, Observer {
					if (it != null) {
						startActivity(EstimationActivity.createIntent(this, it))
					}
				})
	}
}