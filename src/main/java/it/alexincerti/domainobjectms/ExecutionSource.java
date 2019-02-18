package it.alexincerti.domainobjectms;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ExecutionSource {
 
    @Output("execution")
    MessageChannel execution();
 
}