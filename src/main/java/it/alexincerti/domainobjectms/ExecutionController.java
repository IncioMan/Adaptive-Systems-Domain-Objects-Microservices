package it.alexincerti.domainobjectms;

import it.alexincerti.domainobjectms.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExecutionController {
 
    @Autowired
    ExecutionSource executionSource;
 
    @RequestMapping("/send")
    @ResponseBody
    public String orderFood(){
        executionSource.execution().send(MessageBuilder.withPayload(new Message("ciao")).build());
        System.out.println("Message sent");
        return "Message sent";
    }
}