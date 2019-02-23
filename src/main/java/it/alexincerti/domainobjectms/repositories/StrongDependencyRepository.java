package it.alexincerti.domainobjectms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alexincerti.domainobjectms.model.DomainObjectStrongDependency;

@Repository
public interface StrongDependencyRepository extends JpaRepository<DomainObjectStrongDependency, Long> {
	DomainObjectStrongDependency findByDomainObjectExecutorAndExternalDomainObjectInstance(String domainObjectExecutor,
			Long externalDomainObjectInstance);
}