package estimator.kissteam.com.estimatorclient.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import estimator.kissteam.com.estimatorclient.R
import estimator.kissteam.com.estimatorclient.dal.entities.EstimationStrategy
import estimator.kissteam.com.estimatorclient.view.recycler.EstimationStrategiesRecyclerViewAdapter
import estimator.kissteam.com.estimatorclient.viewmodel.CreateRoomBundle
import kotlinx.android.synthetic.main.view_create_room_estimation_strategy.view.*

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class CreateRoomEstimationStrategyView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : CreateRoomAwareView(context) {

	private val recyclerViewAdapter = EstimationStrategiesRecyclerViewAdapter(
			mutableListOf(
					EstimationStrategy(0,
							"Estimate by average",
							"Estimate by average value") to false,
					EstimationStrategy(1,
							"Estimate by the highest value",
							"Estimate by the highest value strategy") to false))

	init {
		val inflater = context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		inflater.inflate(R.layout.view_create_room_estimation_strategy, this, true)
		this.recyclerViewEstimations.layoutManager = LinearLayoutManager(context)
		this.recyclerViewEstimations.adapter = this.recyclerViewAdapter
	}

	fun setEstimationStrategy(strategy: EstimationStrategy) {
		val strategyList = this.recyclerViewAdapter.myDataset.map {
			if (it.first.id == strategy.id) {
				EstimationStrategy(it.first.id, it.first.title, it.first.description) to true
			} else {
				it
			}
		}
		this.recyclerViewAdapter.myDataset.clear()
		this.recyclerViewAdapter.myDataset.addAll(strategyList)
	}

	override fun modifyCreateRoomBundle(createRoomBundle: CreateRoomBundle): CreateRoomBundle {
		val filteredList = recyclerViewAdapter.myDataset.filter { it.second }
		if (filteredList.isNotEmpty()) {
			return createRoomBundle.copy(estimationStrategy = filteredList[0].first)
		} else {
			return createRoomBundle
		}
	}
}
