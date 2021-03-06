package estimator.kissteam.com.estimatorclient.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import estimator.kissteam.com.estimatorclient.R
import estimator.kissteam.com.estimatorclient.dal.entities.User
import estimator.kissteam.com.estimatorclient.dal.gateway.user.GetAllUsersGateway
import estimator.kissteam.com.estimatorclient.view.recycler.UsersRecyclerViewAdapter
import estimator.kissteam.com.estimatorclient.viewmodel.CreateRoomBundle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.view_create_room_users.view.*

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class CreateRoomUsersView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0)
	: CreateRoomAwareView(context) {

	private val recyclerViewAdapter: UsersRecyclerViewAdapter?

	init {
		val inflater = context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		inflater.inflate(R.layout.view_create_room_users, this, true)

		recyclerViewAdapter = UsersRecyclerViewAdapter(mutableListOf())
		loadUserList()
				.subscribe {
					recyclerViewAdapter.myDataset.clear()
					recyclerViewAdapter.myDataset.addAll(it)
					recyclerViewAdapter.notifyDataSetChanged()
				}

		this.recyclerViewUsers.layoutManager = LinearLayoutManager(context)
		this.recyclerViewUsers.adapter = this.recyclerViewAdapter
	}

	fun setUserList(userList: MutableList<User>) {
		loadUserList()
				.map {
					it.map {
						if (userList.map { it.id }.contains(it.first.id) &&
								userList.map { it.email }.contains(it.first.email)) {
							User(it.first.id,
									it.first.email,
									it.first.creationDate,
									it.first.modificationDate) to true
						} else {
							it
						}
					}
				}
				.subscribe {
					recyclerViewAdapter?.myDataset?.clear()
					recyclerViewAdapter?.myDataset?.addAll(it)
					recyclerViewAdapter?.notifyDataSetChanged()
				}
	}

	private fun loadUserList(): Observable<MutableList<Pair<User, Boolean>>> =
			GetAllUsersGateway()
					.execute()
					.map { users ->
						users
								.map { user -> user to false }
								.toMutableList()
					}
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())

	override fun modifyCreateRoomBundle(createRoomBundle: CreateRoomBundle): CreateRoomBundle {
		return createRoomBundle.copy(users = recyclerViewAdapter
				?.myDataset
				?.filter { it.second }
				?.map { it.first } as MutableList)
	}
}