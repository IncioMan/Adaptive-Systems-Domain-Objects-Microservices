package it.alexincerti.domainobjectms.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.alexincerti.domainobjectms.events.ActivityExecuted;
import it.alexincerti.domainobjectms.events.DomainEvent;
import it.alexincerti.domainobjectms.messages.ExecuteActivityMessage;
import it.alexincerti.domainobjectms.model.DomainObjectInstance;
import it.alexincerti.domainobjectms.repositories.DomainObjectInstanceRepository;

@Service
public class ExecutionService {

	@Autowired
	private DomainObjectInstanceRepository domainObjectInstanceRepository;

	public List<DomainEvent> processExecuteActivity(ExecuteActivityMessage executeActivityMessage) {
		// Retrieve DOI
		DomainObjectInstance doi = domainObjectInstanceRepository.getOne(executeActivityMessage.getDomainObjectId());
		// Retrieve ExecutionHandler
		// Run executionHandler
		// Publish ActivityExecuted event
		System.out.println(
				String.format("Executing |%s| on DOI |%d|...", executeActivityMessage.getActivityName(), doi.getId()));
		List<DomainEvent> events = new ArrayList<>();
		events.add(new ActivityExecuted(executeActivityMessage.getActivityName(),
				executeActivityMessage.getDomainObjectId()));
		return events;
	}
}
