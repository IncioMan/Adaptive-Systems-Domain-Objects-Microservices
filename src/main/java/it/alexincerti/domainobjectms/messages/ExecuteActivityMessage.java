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

	private Long domainObjectId;
	private Long activityId;

	public ExecuteActivityMessage() {

	}

	public ExecuteActivityMessage(Long domainObjectId, Long activityId) {
		this.activityId = activityId;
		this.domainObjectId = domainObjectId;
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public Long getDomainObjectId() {
		return domainObjectId;
	}

	public void setDomainObjectId(Long domainObjectId) {
		this.domainObjectId = domainObjectId;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + "{" + "domainObjectId='" + domainObjectId + '\n' + "activityId='"
				+ activityId + '}';
	}
}