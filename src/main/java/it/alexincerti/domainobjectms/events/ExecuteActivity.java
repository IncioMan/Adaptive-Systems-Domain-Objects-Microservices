package it.alexincerti.domainobjectms.events;

public class ExecuteActivity implements DomainEvent {

	private Long activityId;
	private Long domainObjectId;

	public ExecuteActivity(Long activityId, Long domainObjectId) {
		this.activityId = activityId;
		this.domainObjectId = domainObjectId;
	}

	public ExecuteActivity() {
		// TODO Auto-generated constructor stub
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getDomainObjectId() {
		return domainObjectId;
	}

	public void setDomainObjectId(Long domainObjectId) {
		this.domainObjectId = domainObjectId;
	}

}
