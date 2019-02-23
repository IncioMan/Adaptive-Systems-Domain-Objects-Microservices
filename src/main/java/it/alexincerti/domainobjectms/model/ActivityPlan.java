package it.alexincerti.domainobjectms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "activity_plans")
public class ActivityPlan {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne(targetEntity = Activity.class, cascade = { CascadeType.ALL })
	private Activity startActivity;

	@ManyToOne(targetEntity = Activity.class, cascade = { CascadeType.ALL })
	private Activity currentActivity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Activity getStartActivity() {
		return startActivity;
	}

	public void setStartActivity(Activity startActivity) {
		this.startActivity = startActivity;
		this.setCurrentActivity(startActivity);
	}

	public Activity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(Activity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public Activity getNextActivity() {
		return currentActivity;
	}

	public void moveToNextActivity() {
		currentActivity = currentActivity == null ? null : currentActivity.getNextActivity();
	}
}