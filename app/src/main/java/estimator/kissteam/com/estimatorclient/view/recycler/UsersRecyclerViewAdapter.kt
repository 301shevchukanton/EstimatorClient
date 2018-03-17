package estimator.kissteam.com.estimatorclient.view.recycler

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.view.UserItemView

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class UsersRecyclerViewAdapter(val myDataset: MutableList<Pair<User, Boolean>>) :
		RecyclerView.Adapter<UsersRecyclerViewAdapter.ViewHolder>() {

	class ViewHolder(val userListItemView: UserItemView) : RecyclerView.ViewHolder(userListItemView)

	override fun onCreateViewHolder(parent: ViewGroup,
	                                viewType: Int): UsersRecyclerViewAdapter.ViewHolder {
		val userItemView = UserItemView(parent.context)
		return ViewHolder(userItemView)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.userListItemView.setUser(myDataset[position].first, myDataset[position].second)
		holder.userListItemView.setOnClickListener {
			myDataset[position] = myDataset[position].copy(
					second = !myDataset[position].second)
		}
	}

	override fun getItemCount() = myDataset.size

	fun addUser(user: User, checked: Boolean = false) {
		myDataset.add(user to checked)
	}
}