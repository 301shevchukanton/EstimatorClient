package estimator.kissteam.com.estimatorclient.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import estimator.kissteam.com.estimatorclient.viewmodel.CreateRoomBundle;

/**
 * Created by AntonShevchuk on 17.03.2018.
 */

public abstract class CreateRoomAwareView extends LinearLayout {
	public CreateRoomAwareView(Context context) {
		super(context);
	}

	public abstract CreateRoomBundle modifyCreateRoomBundle(CreateRoomBundle createRoomBundle);
}
