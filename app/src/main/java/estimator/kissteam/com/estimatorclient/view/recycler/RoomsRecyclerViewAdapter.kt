package estimator.kissteam.com.estimatorclient.view.recycler

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.view.RoomItemView

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class RoomsRecyclerViewAdapter :
		RecyclerView.Adapter<RoomsRecyclerViewAdapter.ViewHolder>() {
	val clickedRoom: MutableLiveData<Room> = MutableLiveData()
	private val myDataset: MutableList<RoomItem> = mutableListOf()

	class ViewHolder(val taskItemView: RoomItemView) : RecyclerView.ViewHolder(taskItemView)

	fun setData(items: List<RoomItem>): RoomsRecyclerViewAdapter {
		myDataset.clear()
		myDataset.addAll(items.sortedWith(Comparator { t1, t2 ->
			if (t1.totalEstimation == null && t2.totalEstimation != null) {
				return@Comparator -1
			} else if (t1.totalEstimation != null && t2.totalEstimation == null) {
				return@Comparator 1
			} else {
				return@Comparator 0
			}
		}))
		return this
	}

	override fun onCreateViewHolder(parent: ViewGroup,
									viewType: Int): RoomsRecyclerViewAdapter.ViewHolder {
		val taskItemView = RoomItemView(parent.context)
		return ViewHolder(taskItemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.taskItemView.setRoom(myDataset[position])
		holder.taskItemView.setOnClickListener {
			clickedRoom.value = myDataset[position].room
		}
	}

	override fun getItemCount() = myDataset.size
}