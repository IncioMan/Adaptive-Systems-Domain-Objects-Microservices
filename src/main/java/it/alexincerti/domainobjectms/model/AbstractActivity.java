package it.alexincerti.domainobjectms.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractActivity {
	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String description;
	@ManyToOne(targetEntity = AbstractActivity.class, cascade = { CascadeType.ALL })
	private AbstractActivity nextActivity;

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

	public AbstractActivity getNextActivity() {
		return nextActivity;
	}

	public void setNextActivity(AbstractActivity nextActivity) {
		this.nextActivity = nextActivity;
	}
}