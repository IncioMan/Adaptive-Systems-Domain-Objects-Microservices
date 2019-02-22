package it.alexincerti.domainobjectms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

	public ExecuteActivity getExecuteNextActivity() {
		ExecuteActivity executeActivity = new ExecuteActivity(
				"activity" + (Integer.parseInt(getState() != null ? getState() : "0") + 1) + "", this.id);
		return executeActivity;
	}
}