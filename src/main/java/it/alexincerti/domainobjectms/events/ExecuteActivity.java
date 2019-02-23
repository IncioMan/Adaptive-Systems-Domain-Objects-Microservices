package it.alexincerti.domainobjectms.events;

public class ExecuteActivity implements DomainEvent {

	private String activityName;
	private Long domainObjectId;

	public ExecuteActivity(String activityName, Long domainObjectId) {
		this.activityName = activityName;
		this.domainObjectId = domainObjectId;
	}

	public ExecuteActivity() {
		// TODO Auto-generated constructor stub
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Long getDomainObjectId() {
		return domainObjectId;
	}

	public void setDomainObjectId(Long domainObjectId) {
		this.domainObjectId = domainObjectId;
	}

}
