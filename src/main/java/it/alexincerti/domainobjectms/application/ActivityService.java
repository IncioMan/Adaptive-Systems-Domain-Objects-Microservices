package it.alexincerti.domainobjectms.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.alexincerti.domainobjectms.events.DomainEvent;
import it.alexincerti.domainobjectms.messages.ActivityExecutedMessage;
import it.alexincerti.domainobjectms.model.DomainObjectInstance;
import it.alexincerti.domainobjectms.repositories.DomainObjectInstanceRepository;

@Service
public class ActivityService {
	@Autowired
	private DomainObjectInstanceRepository domainObjectInstanceRepository;

	public List<DomainEvent> processActivityExecuted(ActivityExecutedMessage activityExecutedMessage) {
		ArrayList<DomainEvent> events = new ArrayList<>();
		//
		System.out.println(String.format("Activity |%s| executed by DOI |%d|",
				activityExecutedMessage.getActivityName(), activityExecutedMessage.getDomainObjectId()));
		// Load DOI
		Optional<DomainObjectInstance> doi = domainObjectInstanceRepository
				.findById(activityExecutedMessage.getDomainObjectId());
		// Process events from AE event
		String state = doi.get().getState();
		if (state == null) {
			state = "0";
		}
		if (!state.equals("17")) {
			state = Integer.parseInt(state) + 1 + "";
			doi.get().setState(state);
			domainObjectInstanceRepository.save(doi.get());
			System.out.println(String.format("DOI state |%s|", state));
			// Publish new Execute Activity by calling getnextActivity
			events.add(doi.get().getExecuteNextActivity());
		}
		return events;
	}
}
