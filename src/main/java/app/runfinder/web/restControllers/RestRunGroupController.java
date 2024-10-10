package app.runfinder.web.restControllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.repositories.RunGroupRepository;

// @RestController annotates this class as a rest controller class
@RestController
// @RequestMapping sets a default part of the request path
@RequestMapping("/api")
public class RestRunGroupController {

    // @Autowired injects the repository to the controller
    @Autowired
    RunGroupRepository runGroupRepository;

    // @GetMapping creates a GET request
    // Here it lists all the run groups and their attributes
    @GetMapping("/rungroups")
    public Iterable<RunGroup> getRunGroups() {
        return runGroupRepository.findAll();
    }

    // @GetMapping creates a GET request
    // Here it lists a run group specified in @PathVariable
    @GetMapping("rungroups/{runGroupId}")
    public Optional<RunGroup> getRunGroup(@PathVariable Long runGroupId) {
        return runGroupRepository.findById(runGroupId);
    }

    // @PostMapping creates a POST request
    // Here it adds a new run group to runGroupRepository
    @PostMapping("rungroups")
    public RunGroup postRunGroup(@RequestBody RunGroup newRunGroup) {
        runGroupRepository.save(newRunGroup);
        return newRunGroup;
    }

    // @PutMapping creates a PUT request
    // Here it updates the rungroup specified in @PathVariable
    // @RequestBody contains the updated attributes
    @PutMapping("runroups/{runGroupId}")
    public RunGroup putRunGroup(@RequestBody RunGroup editedRunGroup,
            @PathVariable Long runGroupId) {
        editedRunGroup.setRunGroupId(runGroupId);
        runGroupRepository.save(editedRunGroup);
        return editedRunGroup;
    }

    // @DeleteMapping creates a DELETE request
    // Here it deletes the rungroup specified in @PathVariable
    @DeleteMapping("runroups/{runGroupId}")
    public Iterable<RunGroup> deleteRunGroup(@PathVariable Long runGroupId) {
        runGroupRepository.deleteById(runGroupId);
        return runGroupRepository.findAll();
    }
}
