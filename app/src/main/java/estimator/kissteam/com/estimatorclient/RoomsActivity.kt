package estimator.kissteam.com.estimatorclient

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import estimator.kissteam.com.estimatorclient.view.recycler.RoomsRecyclerViewAdapter
import estimator.kissteam.com.estimatorclient.viewmodel.RoomsViewModel
import kotlinx.android.synthetic.main.activity_rooms.*


/**
 * Created by: anna
 * Date: 3/17/18.
 */
class RoomsActivity : AppCompatActivity() {

	private val viewModelClass = RoomsViewModel::class.java

	private val recyclerViewAdapter = RoomsRecyclerViewAdapter()

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
		progressBar.visibility = View.VISIBLE

		btnCreateRoom.setOnClickListener {
			startActivity(Intent(this, CreateRoomActivity::class.java))
		}
		getRoomsViewModel()
				.roomLiveData
				.observe(this, Observer {
					if (it != null) {
						if (it.isEmpty()) {
							placeholder.visibility = View.VISIBLE
						} else {
							placeholder.visibility = View.INVISIBLE
						}
						progressBar.visibility = View.GONE
						this.recyclerViewAdapter.setData(it)
						this.recyclerViewAdapter.notifyDataSetChanged()
					} else {
						progressBar.visibility = View.VISIBLE
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