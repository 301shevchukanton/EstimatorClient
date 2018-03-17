package estimator.kissteam.com.estimatorclient.view.recycler

import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import estimator.kissteam.com.estimatorclient.R
import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.view.RoomItemView

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class RoomsRecyclerViewAdapter(val myDataset: MutableList<Room>) :
        RecyclerView.Adapter<RoomsRecyclerViewAdapter.ViewHolder>() {
    val clickedRoom: MutableLiveData<Room> = MutableLiveData()

    class ViewHolder(val taskItemView: RoomItemView) : RecyclerView.ViewHolder(taskItemView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RoomsRecyclerViewAdapter.ViewHolder {
        val taskItemView = RoomItemView(parent.context)
        return ViewHolder(taskItemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.taskItemView.setRoom(myDataset[position])
        holder.taskItemView.setOnClickListener {
            clickedRoom.value = myDataset[position]
        }
    }

    override fun getItemCount() = myDataset.size

    fun addIssue(room: Room) {
        myDataset.add(room)
    }
}