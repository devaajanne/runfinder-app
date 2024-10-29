package app.runfinder.web.restControllers;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import app.runfinder.domain.entities.Role;
import app.runfinder.domain.repositories.RoleRepository;

@RestController
@RequestMapping("/api")
public class RoleRestController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/roles")
    public Iterable<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @GetMapping("/roles/{id}")
    public Optional<Role> getRoleById(@PathVariable("id") Long id) {
        return roleRepository.findById(id);
    }

}
