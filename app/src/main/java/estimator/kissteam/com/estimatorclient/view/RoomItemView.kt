package estimator.kissteam.com.estimatorclient.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import estimator.kissteam.com.estimatorclient.R
import estimator.kissteam.com.estimatorclient.view.recycler.RoomItem
import kotlinx.android.synthetic.main.view_rooms_item.view.*

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class RoomItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

	init {
		val inflater = context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		inflater.inflate(R.layout.view_rooms_item, this, true)
	}

	fun setRoom(room: RoomItem) {
		tvRoomName.text = room.room.title;
		if (room.totalEstimation != null) {
			tvTotalEstiamtiom.text = "Total estimation: ${room.totalEstimation}"
			tvTotalEstiamtiom.visibility = View.VISIBLE
			ivImage.visibility = View.VISIBLE
		} else {
			tvTotalEstiamtiom.visibility = View.INVISIBLE
			ivImage.visibility = View.INVISIBLE
		}
	}
}