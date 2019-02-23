package it.alexincerti.domainobjectms.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.alexincerti.domainobjectms.dispatcher.EventDispatcher;
import it.alexincerti.domainobjectms.events.ActivityExecuted;
import it.alexincerti.domainobjectms.events.DomainEvent;
import it.alexincerti.domainobjectms.events.ExecuteActivity;

@Service
public class ApplicationService {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ExecutionService executionService;
	@Autowired
	private EventDispatcher eventDispatcher;

	public void processActivityExecuted(ActivityExecuted activityExecuted) {
		List<DomainEvent> events = activityService.processActivityExecuted(activityExecuted);
		eventDispatcher.dispatch(events);
	}

	public void processExecuteActivity(ExecuteActivity executeActivity) {
		List<DomainEvent> events = executionService.processExecuteActivity(executeActivity);
		eventDispatcher.dispatch(events);
	}

	public void processStart() {
		List<DomainEvent> events = executionService.startDomainObject();
		eventDispatcher.dispatch(events);
	}
}
