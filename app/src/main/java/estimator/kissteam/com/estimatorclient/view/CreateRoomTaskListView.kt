package estimator.kissteam.com.estimatorclient.view

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.TextView
import estimator.kissteam.com.estimatorclient.R
import estimator.kissteam.com.estimatorclient.dal.services.request_entity.IssueInformation
import estimator.kissteam.com.estimatorclient.view.recycler.TaskListRecyclerViewAdapter
import estimator.kissteam.com.estimatorclient.viewmodel.CreateRoomBundle
import kotlinx.android.synthetic.main.view_create_room_tasklist.view.*

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class CreateRoomTaskListView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
	: CreateRoomAwareView(context) {

	private val recyclerViewAdapter = TaskListRecyclerViewAdapter(
			mutableListOf(
					IssueInformation(
							"my task title",
							"my task's description")))

	private val clickListener = OnClickListener {
		val addTaskDialogView = AddTaskDialogView(context)

		val dialog = AlertDialog.Builder(context)
				.setView(addTaskDialogView)
				.create()

		addTaskDialogView.liveData
				.observe(context as LifecycleOwner, Observer {
					recyclerViewAdapter.addIssue(IssueInformation(
							it?.first,
							it?.second))
					recyclerViewAdapter.notifyDataSetChanged()
					dialog.cancel()
				})

		dialog.show()
	}

	init {
		val inflater = context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		inflater.inflate(R.layout.view_create_room_tasklist, this, true)

		this.recyclerViewTasks.layoutManager = LinearLayoutManager(context)
		this.recyclerViewTasks.adapter = this.recyclerViewAdapter
		tvAddNewTask.setOnClickListener(this.clickListener)
	}

	fun setTaskList(taskList:MutableList<IssueInformation>)  {
		recyclerViewAdapter.myDataset.clear()
		recyclerViewAdapter.myDataset.addAll(taskList)
		recyclerViewAdapter.notifyDataSetChanged()
	}

	override fun modifyCreateRoomBundle(createRoomBundle: CreateRoomBundle): CreateRoomBundle {
		return createRoomBundle.copy(issueList = recyclerViewAdapter.myDataset) //To change body of created functions use File | Settings | File Templates.
	}
}
