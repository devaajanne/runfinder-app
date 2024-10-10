package app.runfinder.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.RunGroupSignUp;

public interface RunGroupSignUpRepository extends CrudRepository<RunGroupSignUp, Long> {
     // No additional CRUD methods created
}