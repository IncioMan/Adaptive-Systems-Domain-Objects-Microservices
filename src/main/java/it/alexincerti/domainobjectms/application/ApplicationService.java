package it.alexincerti.domainobjectms.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.alexincerti.domainobjectms.dispatcher.EventDispatcher;
import it.alexincerti.domainobjectms.events.DomainEvent;
import it.alexincerti.domainobjectms.messages.ActivityExecutedMessage;
import it.alexincerti.domainobjectms.messages.ExecuteActivityMessage;

@Service
public class ApplicationService {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ExecutionService executionService;
	@Autowired
	private EventDispatcher eventDispatcher;

	public void processActivityExecuted(ActivityExecutedMessage activityExecutedMessage) {
		List<DomainEvent> events = activityService.processActivityExecuted(activityExecutedMessage);
		eventDispatcher.dispatch(events);
	}

	public void processExecuteActivity(ExecuteActivityMessage executeActivityMessage) {
		List<DomainEvent> events = executionService.processExecuteActivity(executeActivityMessage);
		eventDispatcher.dispatch(events);
	}

}
