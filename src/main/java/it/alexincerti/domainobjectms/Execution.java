package it.alexincerti.domainobjectms;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Execution {

	String EXECUTE_ACTIVITY_INPUT = "execute_activity_input";
	String ACTIVITY_EXECUTED_INPUT = "activity_executed_input";

	@Output("execute_activity_output")
	MessageChannel executeActivityOutput();

	@Output("activity_executed_output")
	MessageChannel activityExecutedOutput();

	@Input(EXECUTE_ACTIVITY_INPUT)
	MessageChannel nextActivityInput();

	@Input(ACTIVITY_EXECUTED_INPUT)
	MessageChannel activityExecutedInput();
}