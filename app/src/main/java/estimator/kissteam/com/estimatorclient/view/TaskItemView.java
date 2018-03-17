package estimator.kissteam.com.estimatorclient.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import estimator.kissteam.com.estimatorclient.R;
import estimator.kissteam.com.estimatorclient.dal.services.request_bundle.IssueRequestBundle;

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

public class TaskItemView extends LinearLayout {
	private TextView taskNameText;
	private TextView taskDescriptionText;

	public TaskItemView(Context context) {
		this(context, null);
	}

	public TaskItemView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TaskItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		this(context, attrs, defStyleAttr, 0);
	}

	public TaskItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_task_item, this, true);
		taskNameText = findViewById(R.id.tvTaskName);
		taskDescriptionText = findViewById(R.id.tvTaskDescription);
	}

	public String getTaskName() {
		return taskNameText.getText().toString();
	}

	public String getTaskDescription() {
		return taskDescriptionText.getText().toString();
	}

	public void setIssue(IssueRequestBundle issue) {
		this.taskNameText.setText(issue.getTitle());
		this.taskDescriptionText.setText(issue.getDescription());
	}
}
