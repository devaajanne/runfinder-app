package app.runfinder.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.RunningGroup;

public interface RunningGroupRepository extends CrudRepository<RunningGroup, Long> {
     // No additional CRUD methods created
}