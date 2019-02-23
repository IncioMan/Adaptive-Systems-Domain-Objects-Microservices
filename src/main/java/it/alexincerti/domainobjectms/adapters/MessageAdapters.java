package it.alexincerti.domainobjectms.adapters;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.MessageBuilder;

import it.alexincerti.domainobjectms.application.ApplicationService;
import it.alexincerti.domainobjectms.dispatcher.EventDispatcher;
import it.alexincerti.domainobjectms.events.ActivityExecuted;
import it.alexincerti.domainobjectms.events.ExecuteActivity;
import it.alexincerti.domainobjectms.events.ExecuteExternalActivity;
import it.alexincerti.domainobjectms.events.StrongDependencyEvent;
import it.alexincerti.domainobjectms.messages.ActivityExecutedMessage;
import it.alexincerti.domainobjectms.messages.ExecuteActivityMessage;
import it.alexincerti.domainobjectms.messages.ExecuteActivityPlanMessage;
import it.alexincerti.domainobjectms.messages.Execution;
import it.alexincerti.domainobjectms.messages.StartMessage;
import it.alexincerti.domainobjectms.messages.StrongDependencyMessage;

@EnableBinding(Execution.class)
public class MessageAdapters {
	//
	@Autowired
	private ApplicationService applicationService;
	@Autowired
	private EventDispatcher eventDispatcher;
	@Autowired
	Execution execution;

	@Value("${domainobject.name}")
	private String domainObjectName;

	@PostConstruct
	private void init() {
		eventDispatcher.registerHandler(ActivityExecuted.class, e -> sendActivityExecuted((ActivityExecuted) e));
		eventDispatcher.registerHandler(ExecuteActivity.class, e -> sendExecuteActivity((ExecuteActivity) e));
		eventDispatcher.registerHandler(ExecuteExternalActivity.class,
				e -> sendExecuteExternalActivity((ExecuteExternalActivity) e));
		eventDispatcher.registerHandler(StrongDependencyEvent.class,
				e -> sendStrongDependencyEvent((StrongDependencyEvent) e));
	}

	// @SendTo(Execution.ACTIVITY_EXECUTED_OUTPUT)
	private void sendActivityExecuted(ActivityExecuted event) {
		execution.activityExecutedOutput().send(MessageBuilder.withPayload(convert(event)).build());
	}

	private void sendExecuteActivity(ExecuteActivity event) {
		execution.executeActivityOutput().send(MessageBuilder.withPayload(convert(event)).build());
	}

	private void sendStrongDependencyEvent(StrongDependencyEvent event) {
		execution.executeStrongDependencyOutput().send(MessageBuilder.withPayload(convert(event)).build());
	}

	private void sendExecuteExternalActivity(ExecuteExternalActivity event) {
		execution.executeActivityPlanOutput().send(MessageBuilder.withPayload(convert(event)).build());
	}

	@StreamListener(target = Execution.ACTIVITY_EXECUTED_INPUT)
	public void processMessage(ActivityExecutedMessage activityExecutedMessage) {
		if (activityExecutedMessage.getDomainObjectName().equals(domainObjectName)) {
			applicationService.processActivityExecuted(convert(activityExecutedMessage));
		} else {
			applicationService.processExternalActivityExecuted(convert(activityExecutedMessage));
		}
	}

	@StreamListener(target = Execution.STRONG_DEPENDENCY_INPUT)
	public void processMessage(StrongDependencyMessage strongDependencyMessages) {
		if (strongDependencyMessages.getDomainObjectCaller().equals(domainObjectName)) {
			applicationService.processStrongDependency(convert(strongDependencyMessages));
		}
	}

	@StreamListener(target = Execution.EXECUTE_ACTIVITY_INPUT)
	public void processExecuteActivytMessage(ExecuteActivityMessage executeActivityMessage) {
		applicationService.processExecuteActivity(convert(executeActivityMessage));
	}

	@StreamListener(target = Execution.START_INPUT)
	public void processStartMessage(StartMessage startMessage) {
		applicationService.processStart();
	}

	@StreamListener(target = Execution.EXECUTE_ACTIVITY_PLAN_INPUT)
	public void processStartMessage(ExecuteActivityPlanMessage executeActivityPlanMessage) {
		if (executeActivityPlanMessage.getDomainObjectExecutor().equals(domainObjectName)) {
			applicationService.processExecuteActivityPlan(convert(executeActivityPlanMessage));
		}
	}

	private ExecuteActivity convert(ExecuteActivityMessage message) {
		ExecuteActivity event = new ExecuteActivity();
		event.setActivityId(message.getActivityId());
		event.setDomainObjectId(message.getDomainObjectId());
		return event;
	}

	private ExecuteActivityMessage convert(ExecuteActivity event) {
		ExecuteActivityMessage message = new ExecuteActivityMessage();
		message.setActivityId(event.getActivityId());
		message.setDomainObjectId(event.getDomainObjectId());
		return message;
	}

	private ActivityExecutedMessage convert(ActivityExecuted event) {
		ActivityExecutedMessage message = new ActivityExecutedMessage();
		message.setDomainObjectName(domainObjectName);
		message.setActivityName(event.getActivityName());
		message.setDomainObjectId(event.getDomainObjectId());
		return message;
	}

	private ActivityExecuted convert(ActivityExecutedMessage message) {
		ActivityExecuted event = new ActivityExecuted();
		event.setActivityName(message.getActivityName());
		event.setDomainObjectId(message.getDomainObjectId());
		event.setDomainObjectName(message.getDomainObjectName());
		return event;
	}

	private ExecuteActivityPlanMessage convert(ExecuteExternalActivity event) {
		ExecuteActivityPlanMessage message = new ExecuteActivityPlanMessage();
		message.setActivityName(event.getActivityName());
		message.setDomainObjectId(event.getDomainObjectId());
		message.setDomainObjectCaller(event.getDomainObjectCaller());
		message.setDomainObjectExecutor(event.getDomainObjectExecutor());
		return message;
	}

	private ExecuteExternalActivity convert(ExecuteActivityPlanMessage message) {
		ExecuteExternalActivity event = new ExecuteExternalActivity();
		event.setActivityName(message.getActivityName());
		event.setDomainObjectId(message.getDomainObjectId());
		event.setDomainObjectCaller(message.getDomainObjectCaller());
		event.setDomainObjectExecutor(message.getDomainObjectExecutor());
		return event;
	}

	private StrongDependencyMessage convert(StrongDependencyEvent event) {
		StrongDependencyMessage message = new StrongDependencyMessage();
		message.setDomainObjectCaller(event.getDomainObjectCaller());
		message.setDomainObjectId(event.getDomainObjectId());
		message.setExecutorDomainObjectId(event.getExecutorDomainObjectId());
		message.setDomainObjectExecutor(event.getDomainObjectExecutor());
		return message;
	}

	private StrongDependencyEvent convert(StrongDependencyMessage message) {
		StrongDependencyEvent event = new StrongDependencyEvent();
		event.setDomainObjectCaller(message.getDomainObjectCaller());
		event.setDomainObjectId(message.getDomainObjectId());
		event.setExecutorDomainObjectId(message.getExecutorDomainObjectId());
		event.setDomainObjectExecutor(message.getDomainObjectExecutor());
		return event;
	}
}
