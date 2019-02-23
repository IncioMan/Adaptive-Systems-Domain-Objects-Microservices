package it.alexincerti.domainobjectms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "activities")
public class Activity {
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String description;
	@ManyToOne(targetEntity = Activity.class, cascade = { CascadeType.ALL })
	private Activity nextActivity;

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

	public Activity getNextActivity() {
		return nextActivity;
	}

	public void setNextActivity(Activity nextActivity) {
		this.nextActivity = nextActivity;
	}
}