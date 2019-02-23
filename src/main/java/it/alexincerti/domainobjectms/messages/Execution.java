package it.alexincerti.domainobjectms.messages;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface Execution {

	String EXECUTE_ACTIVITY_INPUT = "execute_activity_input";
	String ACTIVITY_EXECUTED_INPUT = "activity_executed_input";

	String EXECUTE_ACTIVITY_OUTPUT = "execute_activity_output";
	String ACTIVITY_EXECUTED_OUTPUT = "activity_executed_output";

	String START_OUTPUT = "start_output";
	String START_INPUT = "start_input";

	@Output(EXECUTE_ACTIVITY_OUTPUT)
	MessageChannel executeActivityOutput();

	@Output(ACTIVITY_EXECUTED_OUTPUT)
	MessageChannel activityExecutedOutput();

	@Input(EXECUTE_ACTIVITY_INPUT)
	MessageChannel nextActivityInput();

	@Input(ACTIVITY_EXECUTED_INPUT)
	MessageChannel activityExecutedInput();

	@Output(START_OUTPUT)
	MessageChannel startOutput();

	@Input(START_INPUT)
	MessageChannel startInput();
}