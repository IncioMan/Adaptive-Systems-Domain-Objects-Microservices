package it.alexincerti.domainobjectms.dispatcher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import it.alexincerti.domainobjectms.events.DomainEvent;

@Service
public class EventDispatcher {

	Map<Class<? extends DomainEvent>, List<Consumer<DomainEvent>>> handlers = new HashMap<Class<? extends DomainEvent>, List<Consumer<DomainEvent>>>();

	public void dispatch(List<? extends DomainEvent> events) {
		events.forEach(e -> {
			handlers.getOrDefault(e.getClass(), new ArrayList<>()).forEach(handler -> {
				handler.accept(e);
			});
		});
	}

	public <T extends DomainEvent> void registerHandler(Class<T> eventClass, Consumer<DomainEvent> handler) {
		List<Consumer<DomainEvent>> eventHandlers = handlers.getOrDefault(eventClass, new ArrayList<>());
		eventHandlers.add((Consumer<DomainEvent>) handler);
		handlers.put(eventClass, eventHandlers);
	}

}
