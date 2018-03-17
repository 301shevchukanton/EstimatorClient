package estimator.kissteam.com.estimatorclient.view.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.IssueInformationRequestBundle
import estimator.kissteam.com.estimatorclient.view.TaskItemView

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class MyAdapter(private val myDataset: MutableList<IssueInformationRequestBundle>) :
		RecyclerView.Adapter<MyAdapter.ViewHolder>() {

	class ViewHolder(val taskItemView: TaskItemView) : RecyclerView.ViewHolder(taskItemView)

	override fun onCreateViewHolder(parent: ViewGroup,
									viewType: Int): MyAdapter.ViewHolder {
		val taskItemView = TaskItemView(parent.context)
		return ViewHolder(taskItemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.taskItemView.setIssue(myDataset[position])
	}

	override fun getItemCount() = myDataset.size

	fun addTask(issueRequestBundle: IssueInformationRequestBundle) {
		myDataset.add(issueRequestBundle)
	}
}