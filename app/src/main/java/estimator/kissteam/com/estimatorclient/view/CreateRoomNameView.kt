package estimator.kissteam.com.estimatorclient.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout

import estimator.kissteam.com.estimatorclient.R
import estimator.kissteam.com.estimatorclient.viewmodel.CreateRoomBundle

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

class CreateRoomNameView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) : CreateRoomAwareView(context) {
	private val roomNameText: EditText
	private val roomDescriptionText: EditText

	var roomName: String
		get() = roomNameText.text.toString()
		set(name) = this.roomNameText.setText(name)

	var roomDescription: String
		get() = roomDescriptionText.text.toString()
		set(description) = this.roomDescriptionText.setText(description)

	init {
		val inflater = context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		inflater.inflate(R.layout.view_create_room_name, this, true)
		roomNameText = findViewById(R.id.etRoomName)
		roomDescriptionText = findViewById(R.id.etRoomDescription)
	}

	override fun modifyCreateRoomBundle(createRoomBundle: CreateRoomBundle): CreateRoomBundle {
		return createRoomBundle.copy(name = roomName,
				description = roomDescription)
	}
}
