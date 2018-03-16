package estimator.kissteam.com.estimatorclient.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import estimator.kissteam.com.estimatorclient.R;

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

public class CreateRoomNameView extends LinearLayout {
	private EditText roomNameText;
	private EditText roomDescriptionText;

	public CreateRoomNameView(Context context) {
		this(context, null);
	}

	public CreateRoomNameView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CreateRoomNameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		this(context, attrs, defStyleAttr, 0);
	}

	public CreateRoomNameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_create_room_name, this, true);
		roomNameText = findViewById(R.id.etRoomName);
		roomDescriptionText = findViewById(R.id.etRoomDescription);
	}

	public String getRoomName() {
		return roomNameText.getText().toString();
	}

	public String getRoomDescription() {
		return roomDescriptionText.getText().toString();
	}
}
