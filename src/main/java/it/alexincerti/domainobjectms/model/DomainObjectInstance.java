package it.alexincerti.domainobjectms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import it.alexincerti.domainobjectms.events.ActivityExecuted;
import it.alexincerti.domainobjectms.events.ExecuteActivity;

@Entity
@Table(name = "domain_object_instances")
public class DomainObjectInstance {
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String description;
	private String state;

	@ManyToOne(targetEntity = ActivityPlan.class, cascade = { CascadeType.ALL })
	private ActivityPlan activityPlan;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ActivityPlan getActivityPlan() {
		return activityPlan;
	}

	public void setActivityPlan(ActivityPlan activityPlan) {
		this.activityPlan = activityPlan;
	}

	public ExecuteActivity getExecuteNextActivity() {
		if (activityPlan.getNextActivity() == null) {
			return null;
		}
		ExecuteActivity executeActivity = new ExecuteActivity(activityPlan.getNextActivity().getName(), this.id);
		return executeActivity;
	}

	/**
	 * Uses core process as activity plan
	 */
	public void setActivityPlan() {
		ActivityPlan activityPlan = new ActivityPlan();
		Activity currentActivity = null;
		for (int i = 0; i < 4; i++) {
			Activity activity = new Activity();
			activity.setName(i + "");
			if (currentActivity == null) {
				currentActivity = activity;
				activityPlan.setStartActivity(currentActivity);
			} else {
				currentActivity.setNextActivity(activity);
				currentActivity = activity;
			}
		}
		this.activityPlan = activityPlan;
	}

	public void process(ActivityExecuted activityExecuted) {
		if (this.getActivityPlan().getCurrentActivity().getName().equals(activityExecuted.getActivityName())) {
			state = activityExecuted.getActivityName();
			this.getActivityPlan().moveToNextActivity();
		}
	}
}