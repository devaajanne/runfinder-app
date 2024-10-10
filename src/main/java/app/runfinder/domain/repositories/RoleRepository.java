package app.runfinder.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
     // No additional CRUD methods created
}