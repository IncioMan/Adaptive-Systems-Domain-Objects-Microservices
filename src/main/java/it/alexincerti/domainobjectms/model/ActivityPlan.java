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

	@ManyToOne(targetEntity = AbstractActivity.class, cascade = { CascadeType.ALL })
	private AbstractActivity startActivity;

	@ManyToOne(targetEntity = AbstractActivity.class, cascade = { CascadeType.ALL })
	private AbstractActivity currentActivity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AbstractActivity getStartActivity() {
		return startActivity;
	}

	public void setStartActivity(AbstractActivity startActivity) {
		this.startActivity = startActivity;
		this.setCurrentActivity(startActivity);
	}

	public AbstractActivity getCurrentActivity() {
		return currentActivity;
	}

	public void setCurrentActivity(AbstractActivity currentActivity) {
		this.currentActivity = currentActivity;
	}

	public AbstractActivity getNextActivity() {
		return currentActivity;
	}

	public void moveToNextActivity() {
		currentActivity = currentActivity == null ? null : currentActivity.getNextActivity();
	}
}