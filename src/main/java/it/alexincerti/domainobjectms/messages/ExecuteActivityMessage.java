package it.alexincerti.domainobjectms.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/***
 * Command to execute the next activity
 * 
 * @author Alex
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecuteActivityMessage {

	private String activityName;
	private Long domainObjectId;

	public ExecuteActivityMessage() {

	}

	public ExecuteActivityMessage(Long domainObjectId, String activityName) {
		this.activityName = activityName;
		this.domainObjectId = domainObjectId;
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

	@Override
	public String toString() {
		return this.getClass().getName() + "{" + "domainObjectId='" + domainObjectId + '\n' + "activityName='"
				+ activityName + '}';
	}
}