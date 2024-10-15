package app.runfinder.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
     Role findByRole(String role);
}