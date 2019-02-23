package it.alexincerti.domainobjectms.application;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.alexincerti.domainobjectms.events.ActivityExecuted;
import it.alexincerti.domainobjectms.events.DomainEvent;
import it.alexincerti.domainobjectms.events.ExecuteActivity;
import it.alexincerti.domainobjectms.events.ExecuteExternalActivity;
import it.alexincerti.domainobjectms.events.StrongDependencyEvent;
import it.alexincerti.domainobjectms.model.AbstractActivity;
import it.alexincerti.domainobjectms.model.DomainObjectInstance;
import it.alexincerti.domainobjectms.model.DomainObjectStrongDependency;
import it.alexincerti.domainobjectms.model.ExternalActivity;
import it.alexincerti.domainobjectms.model.InternalActivity;
import it.alexincerti.domainobjectms.repositories.AbstractActivityRepository;
import it.alexincerti.domainobjectms.repositories.DomainObjectInstanceRepository;
import it.alexincerti.domainobjectms.repositories.StrongDependencyRepository;

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
	@Autowired
	private AbstractActivityRepository abstractActivityRepository;
	@Autowired
	private StrongDependencyRepository strongDependencyRepository;

	@Value("${domainobject.name}")
	private String domainObjectName;

	public DomainObjectInstanceRepository getDomainObjectInstanceRepository() {
		return domainObjectInstanceRepository;
	}

	public StrongDependencyRepository getStrongDependencyRepository() {
		return strongDependencyRepository;
	}

	public AbstractActivityRepository getAbstractActivityRepository() {
		return abstractActivityRepository;
	}

	public List<DomainEvent> processExecuteActivity(ExecuteActivity executeActivity) {
		List<DomainEvent> events = new ArrayList<>();
		//
		DomainObjectInstance doi = domainObjectInstanceRepository.getOne(executeActivity.getDomainObjectId());
		AbstractActivity activity = getAbstractActivityRepository().findById(executeActivity.getActivityId()).get();
//
		if (activity instanceof InternalActivity) {
			// Retrieve ExecutionHandler
			// Run executionHandler
			LOG.debug("Executing internal activity |{}| on DOI |{}|...", activity.getName(), doi.getId());
			events.add(new ActivityExecuted(domainObjectName, activity.getName(), executeActivity.getDomainObjectId()));
		}
		if (activity instanceof ExternalActivity) {
			LOG.debug("Executing external activity |{}| on DOI |{}|...", activity.getName(), doi.getId());
			events.add(new ExecuteExternalActivity(activity.getName(), domainObjectName,
					((ExternalActivity) activity).getDomainObjectExecutor(), executeActivity.getDomainObjectId()));
		}
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

	public List<DomainEvent> processExecuteActivityPlan(ExecuteExternalActivity executeExternalActivity) {
		List<DomainEvent> events = new ArrayList<>();
		//
		DomainObjectInstance instance = new DomainObjectInstance();
		instance.setActivityPlan(executeExternalActivity.getActivityName());
		instance = getDomainObjectInstanceRepository().save(instance);
		LOG.debug("Instantiated DOI |{}| to execute |{}|...", instance.getId(),
				executeExternalActivity.getActivityName());
		//
		events.add(new StrongDependencyEvent(executeExternalActivity.getDomainObjectCaller(),
				executeExternalActivity.getDomainObjectExecutor(), executeExternalActivity.getDomainObjectId(),
				instance.getId()));
		events.add(instance.getExecuteNextActivity());
		return events;
	}

	public List<DomainEvent> processStrongDependency(StrongDependencyEvent strongDependencyEvent) {
		List<DomainEvent> events = new ArrayList<>();
		//
		DomainObjectStrongDependency strongDependency = new DomainObjectStrongDependency();
		strongDependency.setExternalDomainObjectInstance(strongDependencyEvent.getExecutorDomainObjectId());
		strongDependency.setLocalDomainObjectInstance(strongDependencyEvent.getDomainObjectId());
		strongDependency.setDomainObjectExecutor(strongDependencyEvent.getDomainObjectExecutor());
		strongDependency = getStrongDependencyRepository().save(strongDependency);
		LOG.debug("Strong dependency created: local DOI |{}| depends on DO |{}| DOI |{}|...",
				strongDependency.getLocalDomainObjectInstance(), strongDependency.getDomainObjectExecutor(),
				strongDependency.getExternalDomainObjectInstance());
		return events;
	}

	public List<DomainEvent> processExternalActivityExecuted(ActivityExecuted activityExecuted) {
		List<DomainEvent> events = new ArrayList<>();
		//
		DomainObjectStrongDependency strongDep = getStrongDependencyRepository().//
				findByDomainObjectExecutorAndExternalDomainObjectInstance(//
						activityExecuted.getDomainObjectName(), activityExecuted.getDomainObjectId());
		if (!(strongDep == null)) {
			activityExecuted.setDomainObjectId(strongDep.getLocalDomainObjectInstance());
			activityExecuted.setDomainObjectName(domainObjectName);
			events.add(activityExecuted);
		}
		return events;
	}
}
