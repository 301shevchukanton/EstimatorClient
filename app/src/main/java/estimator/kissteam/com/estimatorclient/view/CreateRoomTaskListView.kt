package estimator.kissteam.com.estimatorclient.view

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View.OnClickListener
import android.widget.LinearLayout
import android.widget.TextView
import estimator.kissteam.com.estimatorclient.R
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.IssueRequestBundle
import estimator.kissteam.com.estimatorclient.view.recycler.MyAdapter

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class CreateRoomTaskListView @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0, defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {
	private val recyclerView: RecyclerView
	private val recyclerViewAdapter = MyAdapter(
			mutableListOf(
					IssueRequestBundle(
							"my task title",
							"my task's description")))
	private val recyclerViewLayoutManager: RecyclerView.LayoutManager
	private val btnAddNewTask: TextView

	private val clickListener = OnClickListener {
		val addTaskDialogView = AddTaskDialogView(context)

		val dialog = AlertDialog.Builder(context)
				.setView(addTaskDialogView)
				.create()

		addTaskDialogView.liveData
				.observe(context as LifecycleOwner, Observer {
					recyclerViewAdapter.addTask(IssueRequestBundle(
							it?.first,
							it?.second))
					dialog.cancel()
				})

		dialog
				.show()
	}

	init {
		val inflater = context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		inflater.inflate(R.layout.view_create_room_tasklist, this, true)

		this.recyclerViewLayoutManager = LinearLayoutManager(context)
		this.recyclerView = findViewById(R.id.recyclerViewTasks)
		this.recyclerView.layoutManager = this.recyclerViewLayoutManager
		this.recyclerView.adapter = this.recyclerViewAdapter
		this.btnAddNewTask = findViewById(R.id.tvAddNewTask)
		this.btnAddNewTask.setOnClickListener(clickListener)
	}

}
