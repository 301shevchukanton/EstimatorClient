package estimator.kissteam.com.estimatorclient.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import estimator.kissteam.com.estimatorclient.R;
import estimator.kissteam.com.estimatorclient.dal.entities.Strategy;

/**
 * Created by AntonShevchuk on 16.03.2018.
 */

public class EstimationStrategyItemView extends LinearLayout {
	private TextView estimationStrategyTitle;
	private TextView estimationStrategyDescription;
	private Strategy estimationStrategy;
	public RadioButton radioButton;

	public EstimationStrategyItemView(Context context) {
		this(context, null);
	}

	public EstimationStrategyItemView(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public EstimationStrategyItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		this(context, attrs, defStyleAttr, 0);
	}

	public EstimationStrategyItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.view_estimation_strategy_item, this, true);
		this.estimationStrategyTitle = findViewById(R.id.tvEstimationStrategyTitle);
		this.estimationStrategyDescription = findViewById(R.id.tvEstimationStrategyDescription);
		this.radioButton = findViewById(R.id.rbEstimationStrategy);
	}

	public void setEstimationStrategy(Strategy estimationStrategy) {
		this.estimationStrategy = estimationStrategy;
		this.estimationStrategyTitle.setText(estimationStrategy.getTitle());
		this.estimationStrategyDescription.setText(estimationStrategy.getDescription());
	}

	public Strategy getEstimationStrategy() {
		return estimationStrategy;
	}
}
