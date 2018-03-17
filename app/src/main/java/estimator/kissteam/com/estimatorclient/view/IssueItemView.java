package estimator.kissteam.com.estimatorclient.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import estimator.kissteam.com.estimatorclient.R;
import estimator.kissteam.com.estimatorclient.dal.entities.Issue;

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

public class IssueItemView extends LinearLayout {
	private TextView issueTitle;
	private TextView issueDescription;
	private TextView progressText;

	public IssueItemView(Context context) {
		this(context, null);
	}

	public IssueItemView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public IssueItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		this(context, attrs, defStyleAttr, 0);
	}

	public IssueItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_estimation_view_pager_item, this, true);
		issueTitle = findViewById(R.id.tvIssueTitle);
		issueDescription = findViewById(R.id.tvIssueDescription);
		progressText = findViewById(R.id.tvIssuesProgress);
	}

	public void setIssue(Issue issue, String progress) {
		issueTitle.setText(issue.getTitle());
		issueDescription.setText(issue.getDescription());
		progressText.setText(progress);

	}
}
