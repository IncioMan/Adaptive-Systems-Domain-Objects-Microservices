package it.alexincerti.domainobjectms.adapters;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;

import it.alexincerti.domainobjectms.application.ApplicationService;
import it.alexincerti.domainobjectms.dispatcher.EventDispatcher;
import it.alexincerti.domainobjectms.events.ActivityExecuted;
import it.alexincerti.domainobjectms.events.ExecuteActivity;
import it.alexincerti.domainobjectms.messages.ActivityExecutedMessage;
import it.alexincerti.domainobjectms.messages.ExecuteActivityMessage;
import it.alexincerti.domainobjectms.messages.Execution;
import it.alexincerti.domainobjectms.messages.StartMessage;

@EnableBinding(Execution.class)
public class MessageAdapters {
	//
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private EventDispatcher eventDispatcher;
	@Autowired
	Execution execution;

	@PostConstruct
	private void init() {
		eventDispatcher.registerHandler(ActivityExecuted.class, e -> sendActivityExecuted((ActivityExecuted) e));
		eventDispatcher.registerHandler(ExecuteActivity.class, e -> sendExecuteActivity((ExecuteActivity) e));
	}

	// @SendTo(Execution.ACTIVITY_EXECUTED_OUTPUT)
	private void sendActivityExecuted(ActivityExecuted event) {
		execution.activityExecutedOutput().send(MessageBuilder.withPayload(convert(event)).build());
	}

	private void sendExecuteActivity(ExecuteActivity event) {
		execution.executeActivityOutput().send(MessageBuilder.withPayload(convert(event)).build());
	}

	@StreamListener(target = Execution.ACTIVITY_EXECUTED_INPUT)
	public void processMessage(ActivityExecutedMessage activityExecutedMessage) {
		applicationService.processActivityExecuted(convert(activityExecutedMessage));
	}

	@StreamListener(target = Execution.EXECUTE_ACTIVITY_INPUT)
	public void processExecuteActivytMessage(ExecuteActivityMessage executeActivityMessage) {
		applicationService.processExecuteActivity(convert(executeActivityMessage));
	}

	@StreamListener(target = Execution.START_INPUT)
	public void processStartMessage(StartMessage startMessage) {
		applicationService.processStart();
	}

	private ExecuteActivity convert(ExecuteActivityMessage message) {
		ExecuteActivity event = new ExecuteActivity();
		event.setActivityName(message.getActivityName());
		event.setDomainObjectId(message.getDomainObjectId());
		return event;
	}

	private ExecuteActivityMessage convert(ExecuteActivity event) {
		ExecuteActivityMessage message = new ExecuteActivityMessage();
		message.setActivityName(event.getActivityName());
		message.setDomainObjectId(event.getDomainObjectId());
		return message;
	}

	private ActivityExecutedMessage convert(ActivityExecuted event) {
		ActivityExecutedMessage message = new ActivityExecutedMessage();
		message.setActivityName(event.getActivityName());
		message.setDomainObjectId(event.getDomainObjectId());
		return message;
	}

	private ActivityExecuted convert(ActivityExecutedMessage message) {
		ActivityExecuted event = new ActivityExecuted();
		event.setActivityName(message.getActivityName());
		event.setDomainObjectId(message.getDomainObjectId());
		return event;
	}
}
