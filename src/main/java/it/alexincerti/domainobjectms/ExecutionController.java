package it.alexincerti.domainobjectms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.alexincerti.domainobjectms.messages.Execution;
import it.alexincerti.domainobjectms.messages.Message;
import it.alexincerti.domainobjectms.messages.StartMessage;

@RestController
public class ExecutionController {

	@Autowired
	Execution execution;

	@RequestMapping("/message")
	@ResponseBody
	public String message() {
		execution.activityExecutedOutput().send(MessageBuilder.withPayload(new Message("ciao")).build());
		System.out.println("Message sent");
		return "Message sent";
	}

	@RequestMapping("/start")
	@ResponseBody
	public String start() {
		for (int i = 0; i < 100; i++) {
			execution.startOutput().send(MessageBuilder.withPayload(new StartMessage()).build());
		}
		System.out.println("DO started");
		return "Message sent";
	}
}