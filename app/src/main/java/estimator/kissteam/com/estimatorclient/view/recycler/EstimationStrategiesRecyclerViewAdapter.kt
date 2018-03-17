package estimator.kissteam.com.estimatorclient.view.recycler

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import estimator.kissteam.com.estimatorclient.dal.entities.EstimationStrategy
import estimator.kissteam.com.estimatorclient.view.EstimationStrategyItemView


/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class EstimationStrategiesRecyclerViewAdapter(
		val myDataset: MutableList<Pair<EstimationStrategy, Boolean>>) :
		RecyclerView.Adapter<EstimationStrategiesRecyclerViewAdapter.ViewHolder>() {


	private var lastChecked: RadioButton? = null
	private var lastCheckedPos = 0

	class ViewHolder(val estimationStrategyItemView: EstimationStrategyItemView) : RecyclerView.ViewHolder(estimationStrategyItemView)

	override fun onCreateViewHolder(parent: ViewGroup,
	                                viewType: Int): EstimationStrategiesRecyclerViewAdapter.ViewHolder {
		val taskItemView = EstimationStrategyItemView(parent.context)
		return ViewHolder(taskItemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.estimationStrategyItemView.setEstimationStrategy(myDataset[position].first)

		if(myDataset[position].second) {
			holder.estimationStrategyItemView.radioButton.isChecked = myDataset[position].second
			lastChecked = holder.estimationStrategyItemView.radioButton
			lastCheckedPos = position
		}
		holder.estimationStrategyItemView.radioButton.setOnClickListener(object : View.OnClickListener {
			override fun onClick(view: View) {
				val radioButton = view as RadioButton
				val clickedPos = position

				if (radioButton.isChecked) {
					if (lastChecked != null && lastCheckedPos != position) {
						lastChecked!!.isChecked = false
						myDataset[lastCheckedPos] = myDataset.get(lastCheckedPos).copy(second = false)
					}
					lastChecked = radioButton
					lastCheckedPos = clickedPos
				} else
					lastChecked = null

				myDataset[clickedPos] = myDataset.get(clickedPos).copy(second = radioButton.isChecked)
			}
		})
	}

	override fun getItemCount() = myDataset.size
}