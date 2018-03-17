package estimator.kissteam.com.estimatorclient

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import estimator.kissteam.com.estimatorclient.dal.entities.Room
import estimator.kissteam.com.estimatorclient.view.view_pager.IssuesViewPagerAdapter
import estimator.kissteam.com.estimatorclient.viewmodel.EstimationViewModel
import kotlinx.android.synthetic.main.activity_estimation.*


/**
 * Created by: anna
 * Date: 3/17/18.
 */
class EstimationActivity : AppCompatActivity() {

	private val viewModelClass = EstimationViewModel::class.java
	private var issuesViewPagerAdapter: IssuesViewPagerAdapter? = null

	private fun getEstimationViewModel() = ViewModelProviders
			.of(this)
			.get(viewModelClass)

	companion object {
		private val ROOM_SERIALIZABLE_KEY = "ROOM_SERIALIZABLE_KEY"
		fun createIntent(context: Context, room: Room? = null): Intent {
			val intent = Intent(context, EstimationActivity::class.java)
			room?.let {
				intent.putExtra(ROOM_SERIALIZABLE_KEY, it)
			}
			return intent
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		getEstimationViewModel().room = intent.getSerializableExtra(ROOM_SERIALIZABLE_KEY) as Room?
		setContentView(R.layout.activity_estimation)
		issuesViewPagerAdapter = IssuesViewPagerAdapter(this)
		vpEstimationViewPager.adapter = issuesViewPagerAdapter
		val onPageListener = DetailOnPageChangeListener()
		vpEstimationViewPager.addOnPageChangeListener(onPageListener)
		getEstimationViewModel()
				.tasksLiveData.observe(this, Observer {
			if (it != null) {
				issuesViewPagerAdapter?.issues?.clear()
				issuesViewPagerAdapter?.issues?.addAll(it)
				issuesViewPagerAdapter?.notifyDataSetChanged()
				if(issuesViewPagerAdapter?.issues?.isNotEmpty() == true) {
					getEstimationViewModel()
							.getUserEstimationForIssue(
									issuesViewPagerAdapter?.issues?.get(onPageListener.currentPage)!!
							)
				}
			}
		})
		getEstimationViewModel().nextTask.observe(this, Observer {
			val newPageIndex =
					if (onPageListener.currentPage < issuesViewPagerAdapter?.issues?.size!!)
						onPageListener.currentPage + 1
					else onPageListener.currentPage
			vpEstimationViewPager.setCurrentItem(newPageIndex)
		})

		getEstimationViewModel()
				.selectCuurentEstimate
				.observe(this, Observer {
					if (it != null) {
						issuesViewPagerAdapter?.issues?.set(onPageListener.currentPage, issuesViewPagerAdapter?.issues?.get(onPageListener.currentPage)?.copy(estimate = it)!!)
						deckView.selectCard(it)
					}
				})
		deckView
				.onItemClick
				.observe(this, Observer {
					if (it != null && issuesViewPagerAdapter?.issues != null) {
						issuesViewPagerAdapter?.issues?.set(onPageListener.currentPage, issuesViewPagerAdapter?.issues?.get(onPageListener.currentPage)?.copy(estimate = it)!!)
						getEstimationViewModel().sendEstimation(
								issuesViewPagerAdapter?.issues?.get(onPageListener.currentPage)?.id?.toString()!!,
								it)
						issuesViewPagerAdapter?.checkForCompletion()
					}
				})
		issuesViewPagerAdapter?.allEstimationsComplete?.observe(this, Observer {
			val alertDialog = AlertDialog.Builder(this)
					.setTitle("Your estimation complete")
					.setMessage("Summary for your estimation is " + it)
					.setPositiveButton("OK", { dialogInterface: DialogInterface,
					                           i: Int ->
						EstimationActivity@this.finish()
					})
					.create()
			alertDialog
					.show()
		})

	}

	inner class DetailOnPageChangeListener : ViewPager.SimpleOnPageChangeListener() {

		var currentPage: Int = 0
			private set

		override fun onPageSelected(position: Int) {
			deckView.clearSelection()
			getEstimationViewModel()
					.getUserEstimationForIssue(
							issuesViewPagerAdapter?.issues?.get(position)!!
					)
			currentPage = position
		}
	}
}