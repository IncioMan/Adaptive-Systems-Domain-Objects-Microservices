package it.alexincerti.domainobjectms.events;

public class ExecuteExternalActivity implements DomainEvent {

	private String activityName;
	private String domainObjectCaller;
	private String domainObjectExecutor;
	private Long domainObjectId;

	public ExecuteExternalActivity() {
		// TODO Auto-generated constructor stub
	}

	public ExecuteExternalActivity(String activityName, String domainObjectCaller, String domainObjectExecutor,
			Long domainObjectId) {
		super();
		this.activityName = activityName;
		this.domainObjectCaller = domainObjectCaller;
		this.domainObjectExecutor = domainObjectExecutor;
		this.domainObjectId = domainObjectId;
	}

	public String getDomainObjectCaller() {
		return domainObjectCaller;
	}

	public void setDomainObjectCaller(String domainObjectCaller) {
		this.domainObjectCaller = domainObjectCaller;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDomainObjectExecutor() {
		return domainObjectExecutor;
	}

	public void setDomainObjectExecutor(String domainObjectExecutor) {
		this.domainObjectExecutor = domainObjectExecutor;
	}

	public Long getDomainObjectId() {
		return domainObjectId;
	}

	public void setDomainObjectId(Long domainObjectId) {
		this.domainObjectId = domainObjectId;
	}

}
