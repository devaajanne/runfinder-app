package app.runfinder.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.AppUser;

// Provdes CRUD functions to the entity class
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    // No additional CRUD methods created
}