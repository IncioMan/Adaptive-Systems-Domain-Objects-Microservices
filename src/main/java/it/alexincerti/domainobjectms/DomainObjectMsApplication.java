package it.alexincerti.domainobjectms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.alexincerti.domainobjectms.repositories.ActivityRepository;

@SpringBootApplication
public class DomainObjectMsApplication {

	@Autowired
	private ActivityRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(DomainObjectMsApplication.class, args);
	}
}
