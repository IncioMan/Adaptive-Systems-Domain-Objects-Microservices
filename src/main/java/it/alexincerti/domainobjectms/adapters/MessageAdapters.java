package it.alexincerti.domainobjectms.adapters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

import it.alexincerti.domainobjectms.Execution;
import it.alexincerti.domainobjectms.application.ApplicationService;
import it.alexincerti.domainobjectms.messages.ActivityExecutedMessage;
import it.alexincerti.domainobjectms.messages.ExecuteActivityMessage;

@EnableBinding(Execution.class)
public class MessageAdapters {
	@Autowired
	private ApplicationService applicationService;

	@StreamListener(target = Execution.ACTIVITY_EXECUTED_INPUT)
	public void processMessage(ActivityExecutedMessage activityExecutedMessage) {
		applicationService.processActivityExecuted(activityExecutedMessage);
	}

	@StreamListener(target = Execution.EXECUTE_ACTIVITY_INPUT)
	public void processExecuteActivytMessage(ExecuteActivityMessage executeActivityMessage) {
		applicationService.processExecuteActivity(executeActivityMessage);
	}
}
