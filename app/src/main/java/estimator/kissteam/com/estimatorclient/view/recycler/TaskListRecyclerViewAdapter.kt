package estimator.kissteam.com.estimatorclient.view.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.IssueRequestBundle
import estimator.kissteam.com.estimatorclient.view.TaskItemView

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class TaskListRecyclerViewAdapter(val myDataset: MutableList<IssueRequestBundle>) :
		RecyclerView.Adapter<TaskListRecyclerViewAdapter.ViewHolder>() {

	class ViewHolder(val taskItemView: TaskItemView) : RecyclerView.ViewHolder(taskItemView)

	override fun onCreateViewHolder(parent: ViewGroup,
	                                viewType: Int): TaskListRecyclerViewAdapter.ViewHolder {
		val taskItemView = TaskItemView(parent.context)
		return ViewHolder(taskItemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.taskItemView.setIssue(myDataset[position])
	}

	override fun getItemCount() = myDataset.size

	fun addIssue(issue: IssueRequestBundle) {
		myDataset.add(issue)
	}
}