package it.alexincerti.domainobjectms.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/***
 * Command to start execution of the DO using his core process
 * 
 * @author Alex
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StartMessage {

	public StartMessage() {

	}

	@Override
	public String toString() {
		return this.getClass().getName() + "{" + '}';
	}
}