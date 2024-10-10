package app.runfinder.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.RunGroup;

public interface RunGroupRepository extends CrudRepository<RunGroup, Long> {
     // No additional CRUD methods created
}