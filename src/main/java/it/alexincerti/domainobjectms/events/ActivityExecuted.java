package it.alexincerti.domainobjectms.events;

public class ActivityExecuted implements DomainEvent {

	private String domainObjectName;
	private String activityName;
	private Long domainObjectId;

	public ActivityExecuted(String domainObjectName, String activityName, Long domainObjectId) {
		super();
		this.domainObjectName = domainObjectName;
		this.activityName = activityName;
		this.domainObjectId = domainObjectId;
	}

	public ActivityExecuted() {
		// TODO Auto-generated constructor stub
	}

	public String getDomainObjectName() {
		return domainObjectName;
	}

	public void setDomainObjectName(String domainObjectName) {
		this.domainObjectName = domainObjectName;
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
