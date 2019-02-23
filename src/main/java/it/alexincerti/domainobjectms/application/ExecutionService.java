package it.alexincerti.domainobjectms.application;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.alexincerti.domainobjectms.events.ActivityExecuted;
import it.alexincerti.domainobjectms.events.DomainEvent;
import it.alexincerti.domainobjectms.events.ExecuteActivity;
import it.alexincerti.domainobjectms.model.DomainObjectInstance;
import it.alexincerti.domainobjectms.repositories.DomainObjectInstanceRepository;

/**
 * Handles the Business Logic for execution of DOs
 * 
 * @author Alex
 *
 */
@Service
public class ExecutionService {

	private static Logger LOG = LoggerFactory.getLogger(ExecutionService.class);

	@Autowired
	private DomainObjectInstanceRepository domainObjectInstanceRepository;

	public DomainObjectInstanceRepository getDomainObjectInstanceRepository() {
		return domainObjectInstanceRepository;
	}

	public List<DomainEvent> processExecuteActivity(ExecuteActivity executeActivity) {
		// Retrieve DOI
		DomainObjectInstance doi = domainObjectInstanceRepository.getOne(executeActivity.getDomainObjectId());
		// Retrieve ExecutionHandler
		// Run executionHandler
		// Publish ActivityExecuted event
		LOG.debug("Executing |{}| on DOI |{}|...", executeActivity.getActivityName(), doi.getId());
		List<DomainEvent> events = new ArrayList<>();
		events.add(new ActivityExecuted(executeActivity.getActivityName(), executeActivity.getDomainObjectId()));
		return events;
	}

	public List<DomainEvent> startDomainObject() {
		List<DomainEvent> events = new ArrayList<>();
		//
		DomainObjectInstance instance = new DomainObjectInstance();
		instance.setActivityPlan();
		instance = getDomainObjectInstanceRepository().save(instance);
		LOG.debug("Instantiated DOI |{}|...", instance.getId());
		//
		events.add(instance.getExecuteNextActivity());
		return events;
	}
}
