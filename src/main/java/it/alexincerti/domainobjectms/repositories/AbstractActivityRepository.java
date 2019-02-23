package it.alexincerti.domainobjectms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alexincerti.domainobjectms.model.AbstractActivity;

@Repository
public interface AbstractActivityRepository extends JpaRepository<AbstractActivity, Long> {
}