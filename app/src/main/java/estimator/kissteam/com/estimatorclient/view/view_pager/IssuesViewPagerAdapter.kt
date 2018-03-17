package estimator.kissteam.com.estimatorclient.view.view_pager

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import estimator.kissteam.com.estimatorclient.dal.entities.Issue
import estimator.kissteam.com.estimatorclient.view.IssueItemView

class IssuesViewPagerAdapter(private val context: Context) : PagerAdapter() {
	var issues = mutableListOf<Issue>()

	val allEstimationsComplete = MutableLiveData<Int>()

	fun checkForCompletion() {
		val initialSize = issues.size
		val estimatedSize = issues.filter {
			it.estimate!= null
		}.size
		if(estimatedSize == initialSize) {
			var sum = 0f
			issues.map {
				it.estimate
			}.forEach {
				sum += it!!
			}
			allEstimationsComplete.value = sum.toInt()
		}
	}

	override fun instantiateItem(collection: ViewGroup, position: Int): Any {
		val issue = issues[position]
		val issueItemView = IssueItemView(context)
		issueItemView.setIssue(issue, (position + 1).toString() + "/" + issues.size)
		collection.addView(issueItemView)
		return issueItemView
	}

	override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
		collection.removeView(view as View)
	}

	override fun getCount(): Int {
		return issues.size
	}

	override fun isViewFromObject(view: View, `object`: Any): Boolean {
		return view === `object`
	}

	override fun getPageTitle(position: Int): CharSequence {
		return (position + 1).toString() + "/" + issues.size
	}

}