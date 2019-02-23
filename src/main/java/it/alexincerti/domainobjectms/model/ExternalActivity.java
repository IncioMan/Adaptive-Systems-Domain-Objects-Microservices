package it.alexincerti.domainobjectms.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "external_activities")
public class ExternalActivity extends AbstractActivity {
	private String domainObjectExecutor;

	public String getDomainObjectExecutor() {
		return domainObjectExecutor;
	}

	public void setDomainObjectExecutor(String domainObjectExecutor) {
		this.domainObjectExecutor = domainObjectExecutor;
	}
}