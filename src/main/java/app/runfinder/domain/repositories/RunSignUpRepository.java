package app.runfinder.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.RunSignUp;

public interface RunSignUpRepository extends CrudRepository<RunSignUp, Long> {
     // No additional CRUD methods created
}