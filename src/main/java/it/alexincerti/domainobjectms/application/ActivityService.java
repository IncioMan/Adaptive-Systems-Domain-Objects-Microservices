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

@Service
public class ActivityService {
	private static Logger LOG = LoggerFactory.getLogger(ActivityService.class);

	@Autowired
	private DomainObjectInstanceRepository domainObjectInstanceRepository;

	public List<DomainEvent> processActivityExecuted(ActivityExecuted activityExecuted) {
		ArrayList<DomainEvent> events = new ArrayList<>();
		//
		LOG.debug("Activity |{}| executed by DOI |{}|", activityExecuted.getActivityName(),
				activityExecuted.getDomainObjectId());
		// Load DOI
		DomainObjectInstance doi = domainObjectInstanceRepository.findById(activityExecuted.getDomainObjectId())
				.orElseGet(null);
		if (doi == null) {
			LOG.error("Unable to find DOI with ID: |{}|...", activityExecuted.getDomainObjectId());
		}
		// Process events from AE event
		doi.process(activityExecuted);
		domainObjectInstanceRepository.save(doi);
		LOG.debug("DOI state |{}|", doi.getState());

		// Publish new Execute Activity by calling getnextActivity
		ExecuteActivity executeNextActivity = doi.getExecuteNextActivity();
		if (executeNextActivity != null) {
			events.add(executeNextActivity);
		}
		return events;
	}
}
