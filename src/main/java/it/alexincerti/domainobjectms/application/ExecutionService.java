package it.alexincerti.domainobjectms.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.alexincerti.domainobjectms.events.ActivityExecuted;
import it.alexincerti.domainobjectms.events.DomainEvent;
import it.alexincerti.domainobjectms.messages.ExecuteActivityMessage;

@Service
public class ExecutionService {

	public List<DomainEvent> processExecuteActivity(ExecuteActivityMessage executeActivityMessage) {
		System.out.println(executeActivityMessage);
		System.out.println("Retrieve DOI");
		System.out.println("Retrieve ExecutionHandler");
		System.out.println("Run executionHandler");
		System.out.println("Publish ActivityExecuted event");
		List<DomainEvent> events = new ArrayList<>();
		events.add(new ActivityExecuted(executeActivityMessage.getActivityName(),
				executeActivityMessage.getDomainObjectId()));
		return events;
	}
}
