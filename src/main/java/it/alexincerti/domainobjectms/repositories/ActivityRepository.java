package it.alexincerti.domainobjectms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.alexincerti.domainobjectms.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
}