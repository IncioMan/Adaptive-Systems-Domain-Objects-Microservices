package it.alexincerti.domainobjectms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alexincerti.domainobjectms.model.DomainObjectInstance;

@Repository
public interface DomainObjectInstanceRepository extends JpaRepository<DomainObjectInstance, Long> {
}