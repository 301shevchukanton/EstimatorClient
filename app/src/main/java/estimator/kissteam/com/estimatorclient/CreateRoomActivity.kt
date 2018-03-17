package estimator.kissteam.com.estimatorclient

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toast
import estimator.kissteam.com.estimatorclient.view.*
import estimator.kissteam.com.estimatorclient.viewmodel.CreateRoomBundle
import estimator.kissteam.com.estimatorclient.viewmodel.CreateRoomViewModel
import kotlinx.android.synthetic.main.activity_create_room.*


class CreateRoomActivity : AppCompatActivity() {

	private val viewModelClass = CreateRoomViewModel::class.java
	private var viewList: List<View>? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_create_room)

		var currentCreateRoomBundle = getRoomViewModel().createRoomBundle

		viewList = createViews(currentCreateRoomBundle)

		container.addView(viewList!![getRoomViewModel().indexOfCurrentView], MATCH_PARENT, WRAP_CONTENT)
		btnNext.setOnClickListener {
			viewList
					?.map { it as? CreateRoomAwareView }
					?.forEach {
						currentCreateRoomBundle = it?.modifyCreateRoomBundle(currentCreateRoomBundle ?: CreateRoomBundle())
					}
			getRoomViewModel().createRoomBundle = currentCreateRoomBundle
			trySwitchToTheNextView(viewList!!)
		}
		getRoomViewModel()
				.roomCreated
				.observe(this, Observer {
					Toast.makeText(this, "Room created", Toast.LENGTH_SHORT).show()
					finish()
				})
		getRoomViewModel()
				.roomCreationError
				.observe(this, Observer { error ->
					Toast.makeText(this, "Room creation error.\nCause: ${error?.message}", Toast.LENGTH_SHORT).show()
				})
	}

	override fun onDestroy() {
		updateViewModelStateByViewsStates(this.viewList)
		super.onDestroy()
	}

	private fun updateViewModelStateByViewsStates(viewList: List<View>?) {
		var currentCreateRoomBundle: CreateRoomBundle = getRoomViewModel().createRoomBundle ?: CreateRoomBundle()
		viewList
				?.map { it as? CreateRoomAwareView }
				?.forEach {
					currentCreateRoomBundle = it?.modifyCreateRoomBundle(currentCreateRoomBundle)!!
				}
		getRoomViewModel().createRoomBundle = currentCreateRoomBundle
	}

	private fun createViews(createRoomBundle: CreateRoomBundle?): List<View> {

		val createRoomNameView = CreateRoomNameView(this)
		createRoomBundle?.description?.let {
			createRoomNameView.roomDescription = it
		}
		createRoomBundle?.name?.let {
			createRoomNameView.roomName = it
		}

		val createRoomTaskListView = CreateRoomTaskListView(this)
		createRoomBundle?.issueList?.let {
			createRoomTaskListView.setTaskList(it)
		}

		val estimationStrategyView = CreateRoomEstimationStrategyView(this)
		createRoomBundle?.estimationStrategy?.let {
			estimationStrategyView.setEstimationStrategy(createRoomBundle.estimationStrategy)
		}

		val usersCreateRoomView = CreateRoomUsersView(this)
		createRoomBundle?.users?.let {
			usersCreateRoomView.setUserList(it)
		}

		val listOfViews = listOf(createRoomNameView,
				createRoomTaskListView,
				estimationStrategyView,
				usersCreateRoomView)
		updateViewModelStateByViewsStates(listOfViews)

		return listOfViews
	}

	private fun trySwitchToTheNextView(viewsList: List<View>) {
		if (getRoomViewModel().indexOfCurrentView < viewsList.size - 1) {
			container.removeAllViews()
			getRoomViewModel().indexOfCurrentView++
			val view = viewsList[getRoomViewModel().indexOfCurrentView]
			container.addView(view)
		} else {
			getRoomViewModel().createRoom()
		}
	}

	private fun getRoomViewModel() = ViewModelProviders
			.of(this)
			.get(viewModelClass)

	override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			onBackPressed()
			return true
		}
		return super.onKeyDown(keyCode, event)
	}


	override fun onBackPressed() {
		if (getRoomViewModel().indexOfCurrentView > 0) {
			container.removeAllViews()
			getRoomViewModel().indexOfCurrentView--
			val view = viewList?.get(getRoomViewModel()
					.indexOfCurrentView)
			container.addView(view)
		} else {
			super.onBackPressed()
		}
	}
}
