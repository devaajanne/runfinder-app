package app.runfinder.domain;

import org.springframework.data.repository.CrudRepository;

// Provdes CRUD functions to the entity class
public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    // No additional CRUD methods created
}