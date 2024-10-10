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

import app.runfinder.domain.entities.RunningGroup;
import app.runfinder.domain.repositories.RunningGroupRepository;

// @RestController annotates this class as a rest controller class
@RestController
// @RequestMapping sets a default part of the request path
@RequestMapping("/api")
public class RestRunningGroupController {

    // @Autowired injects the repository to the controller
    @Autowired
    RunningGroupRepository runningGroupRepository;

    // @GetMapping creates a GET request
    // Here it lists all the running groups and their attributes
    @GetMapping("/runninggroups")
    public Iterable<RunningGroup> getRunningGroups() {
        return runningGroupRepository.findAll();
    }

    // @GetMapping creates a GET request
    // Here it lists a running group specified in @PathVariable
    @GetMapping("runninggroups/{runningGroupId}")
    public Optional<RunningGroup> getRunningGroup(@PathVariable Long runningGroupId) {
        return runningGroupRepository.findById(runningGroupId);
    }

    // @PostMapping creates a POST request
    // Here it adds a new running group to runningGroupRepository
    @PostMapping("runninggroups")
    public RunningGroup postRunningGroup(@RequestBody RunningGroup newRunningGroup) {
        runningGroupRepository.save(newRunningGroup);
        return newRunningGroup;
    }

    // @PutMapping creates a PUT request
    // Here it updates the runninggroup specified in @PathVariable
    // @RequestBody contains the updated attributes
    @PutMapping("runninggroups/{runningGroupId}")
    public RunningGroup putRunningGroup(@RequestBody RunningGroup editedRunningGroup,
            @PathVariable Long runningGroupId) {
        editedRunningGroup.setRunningGroupId(runningGroupId);
        runningGroupRepository.save(editedRunningGroup);
        return editedRunningGroup;
    }

    // @DeleteMapping creates a DELETE request
    // Here it deletes the runninggroup specified in @PathVariable
    @DeleteMapping("runninggroups/{runningGroupId}")
    public Iterable<RunningGroup> deleteRunningGroup(@PathVariable Long runningGroupId) {
        runningGroupRepository.deleteById(runningGroupId);
        return runningGroupRepository.findAll();
    }
}
