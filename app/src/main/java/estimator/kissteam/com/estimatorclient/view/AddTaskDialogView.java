package estimator.kissteam.com.estimatorclient.view;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import estimator.kissteam.com.estimatorclient.R;

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

public class AddTaskDialogView extends LinearLayout {
	private TextView taskNameText;
	private TextView taskDescriptionText;
	public MutableLiveData<Pair<String, String>> liveData = new MutableLiveData<>();

	public AddTaskDialogView(Context context) {
		this(context, null);
	}

	public AddTaskDialogView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public AddTaskDialogView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		this(context, attrs, defStyleAttr, 0);
	}

	public AddTaskDialogView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_create_task_dialog, this, true);
		taskNameText = findViewById(R.id.etTaskName);
		taskDescriptionText = findViewById(R.id.etTaskDescription);

		findViewById(R.id.btnAddTask).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				liveData.setValue(new Pair<String, String>(
						taskNameText.getText().toString(),
						taskDescriptionText.getText().toString()));
			}
		});
	}

}
