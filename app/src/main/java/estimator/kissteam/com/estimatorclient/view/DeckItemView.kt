package estimator.kissteam.com.estimatorclient.view

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.TextView
import estimator.kissteam.com.estimatorclient.R
import kotlinx.android.synthetic.main.view_deck.view.*

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class DeckItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

	val onItemClick = MutableLiveData<Float>()
	val views: MutableList<TextView> = mutableListOf()

	init {
		val inflater = context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		inflater.inflate(R.layout.view_deck, this, true)
		val cards = listOf(0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89)
		val cards1 = cards.subList(0, 5)
		val cards2 = cards.subList(5, cards.size)

		cards1.forEach {
			addCardView(context, it, container1)
		}
		cards2.forEach {
			addCardView(context, it, container2)
		}
	}

	private fun addCardView(context: Context, it: Int, container: LinearLayout) {
		val cardView = TextView(context)
		cardView.text = it.toString()
		cardView.id = it
		cardView.tag = it
		cardView.textSize = 26F
		cardView.gravity = Gravity.CENTER
		val params = LayoutParams(0,
				MATCH_PARENT,
				1f)

		params.setMargins(0, 10, 0, 10)
		//cardView.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
		cardView.setBackgroundResource(R.drawable.ic_card)
		cardView.layoutParams = params
		cardView.setTypeface(null, Typeface.BOLD)
		cardView.setTag("")
		cardView.setOnClickListener {
			onItemClick.value = java.lang.Float.parseFloat((cardView.text.toString()))
		}
		container.addView(cardView)
		views.add(cardView)
		container.invalidate()
	}

	fun selectCard(cardValue: Float) {
		this.views
				.forEach{
					it.setBackgroundColor(resources.getColor(android.R.color.holo_green_dark))
				}
		this.views
				.filter{
					it.text == cardValue.toInt().toString()
				}
				.forEach{
					it.setBackgroundColor(resources.getColor(android.R.color.holo_blue_dark))
				}
	}
}
