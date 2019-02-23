package it.alexincerti.domainobjectms;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import it.alexincerti.domainobjectms.application.ExecutionService;
import it.alexincerti.domainobjectms.messages.ActivityExecutedMessage;
import it.alexincerti.domainobjectms.model.DomainObjectInstance;
import it.alexincerti.domainobjectms.repositories.DomainObjectInstanceRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainObjectMsApplicationTests {

	@Autowired
	ExecutionService executionService;

	@Autowired
	DomainObjectInstanceRepository domainObjectInstanceRepository;

	ActivityExecutedMessage activityExecutedMessage = null;
	DomainObjectInstance domainObjectInstance = null;

	@Test
	public void processExecuteActivityLeadsToActivityExecuted() throws InterruptedException {
		// Given

		domainObjectInstance = createDomainObjectInstance();
		// When
//		List<DomainEvent> events = processExecuteActivityMessage();
//		// Then
//		Assert.isTrue(events.stream()//
//				.filter(e -> e instanceof ActivityExecuted)//
//				.filter(e -> ((ActivityExecuted) e).getDomainObjectId().equals(domainObjectInstance.getId()))//
//				.filter(e -> ((ActivityExecuted) e).getDomainObjectId().equals(domainObjectInstance.getId()))//
//				.count() == 1, "");
	}

	@After
	public void cleanUp() {
		domainObjectInstanceRepository.delete(domainObjectInstance);
	}

	private DomainObjectInstance createDomainObjectInstance() {
		DomainObjectInstance instance = new DomainObjectInstance();
		instance.setName("doi-1");
		return domainObjectInstanceRepository.save(instance);
	}

//	private List<DomainEvent> processExecuteActivityMessage() {
//		return executionService.processExecuteActivity(new ExecuteActivity("ciao", domainObjectInstance.getId()));
//	}
}
