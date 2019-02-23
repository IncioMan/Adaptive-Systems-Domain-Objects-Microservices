package it.alexincerti.domainobjectms.events;

public class StrongDependencyEvent implements DomainEvent {

	private String domainObjectCaller;
	private String domainObjectExecutor;
	private Long domainObjectId;
	private Long executorDomainObjectId;

	public StrongDependencyEvent() {
		// TODO Auto-generated constructor stub
	}

	public StrongDependencyEvent(String domainObjectCaller, String domainObjectExecutor, Long domainObjectId,
			Long executorDomainObjectId) {
		super();
		this.domainObjectCaller = domainObjectCaller;
		this.domainObjectExecutor = domainObjectExecutor;
		this.domainObjectId = domainObjectId;
		this.executorDomainObjectId = executorDomainObjectId;
	}

	public String getDomainObjectExecutor() {
		return domainObjectExecutor;
	}

	public void setDomainObjectExecutor(String domainObjectExecutor) {
		this.domainObjectExecutor = domainObjectExecutor;
	}

	public String getDomainObjectCaller() {
		return domainObjectCaller;
	}

	public void setDomainObjectCaller(String domainObjectCaller) {
		this.domainObjectCaller = domainObjectCaller;
	}

	public Long getExecutorDomainObjectId() {
		return executorDomainObjectId;
	}

	public void setExecutorDomainObjectId(Long executorDomainObjectId) {
		this.executorDomainObjectId = executorDomainObjectId;
	}

	public Long getDomainObjectId() {
		return domainObjectId;
	}

	public void setDomainObjectId(Long domainObjectId) {
		this.domainObjectId = domainObjectId;
	}

}
