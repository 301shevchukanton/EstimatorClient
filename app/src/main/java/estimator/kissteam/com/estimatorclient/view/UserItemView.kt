package estimator.kissteam.com.estimatorclient.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView

import estimator.kissteam.com.estimatorclient.R
import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.services.request_entity.IssueInformation
import kotlinx.android.synthetic.main.view_user_item.view.*

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class UserItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

	init {
		val inflater = context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		inflater.inflate(R.layout.view_user_item, this, true)
	}

	fun setUser(user: User, checked : Boolean = false) {
		tvUsername.text = user.username
		cbIncluded.isChecked = checked
	}
}
