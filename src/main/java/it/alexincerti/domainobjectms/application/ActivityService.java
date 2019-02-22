package it.alexincerti.domainobjectms.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import it.alexincerti.domainobjectms.events.DomainEvent;
import it.alexincerti.domainobjectms.messages.ActivityExecutedMessage;

@Service
public class ActivityService {
	public List<DomainEvent> processActivityExecuted(ActivityExecutedMessage activityExecutedMessage) {
		System.out.println(activityExecutedMessage);
		return new ArrayList<>();
	}
}
