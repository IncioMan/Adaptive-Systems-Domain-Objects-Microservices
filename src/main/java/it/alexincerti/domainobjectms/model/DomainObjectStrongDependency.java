package it.alexincerti.domainobjectms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domain_objects_strong_dependencies")
public class DomainObjectStrongDependency {
	@Id
	@GeneratedValue
	private Long id;

	private String domainObjectExecutor;
	private Long localDomainObjectInstance;
	private Long externalDomainObjectInstance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLocalDomainObjectInstance() {
		return localDomainObjectInstance;
	}

	public void setLocalDomainObjectInstance(Long localDomainObjectInstance) {
		this.localDomainObjectInstance = localDomainObjectInstance;
	}

	public Long getExternalDomainObjectInstance() {
		return externalDomainObjectInstance;
	}

	public void setExternalDomainObjectInstance(Long externalDomainObjectInstance) {
		this.externalDomainObjectInstance = externalDomainObjectInstance;
	}

	public String getDomainObjectExecutor() {
		return domainObjectExecutor;
	}

	public void setDomainObjectExecutor(String domainObjectExecutor) {
		this.domainObjectExecutor = domainObjectExecutor;
	}

}
