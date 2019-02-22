package it.alexincerti.domainobjectms.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/***
 * Notify of the completed execution of an activity
 * 
 * @author Alex
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivityExecutedMessage {

	private String activityName;
	private Long domainObjectId;

	public ActivityExecutedMessage() {

	}

	public ActivityExecutedMessage(Long domainObjectId, String activityName) {
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