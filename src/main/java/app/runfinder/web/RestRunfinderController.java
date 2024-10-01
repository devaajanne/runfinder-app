package app.runfinder.web;

import org.springframework.web.bind.annotation.RestController;

import app.runfinder.domain.RunningGroup;
import app.runfinder.domain.RunningGroupRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class RestRunfinderController {

    @Autowired
    RunningGroupRepository runningGroupRepository;

    @GetMapping("/runninggroups")
    public Iterable<RunningGroup> getRunningGroups() {
        return runningGroupRepository.findAll();
    }

    @GetMapping("runninggroups/{runningGroupId}")
    public Optional<RunningGroup> getRunningGroup(@PathVariable Long runningGroupId) {
        return runningGroupRepository.findById(runningGroupId);
    }

    @PostMapping("runninggroups")
    public RunningGroup postRunningGroup(@RequestBody RunningGroup newRunningGroup) {
        runningGroupRepository.save(newRunningGroup);
        return newRunningGroup;
    }

    @PutMapping("runninggroups/{runningGroupId}")
    public RunningGroup putRunningGroup(@RequestBody RunningGroup editedRunningGroup, @PathVariable Long runningGroupId) {
        editedRunningGroup.setRunningGroupId(runningGroupId);
        runningGroupRepository.save(editedRunningGroup);
        return editedRunningGroup;
    }
    
    @DeleteMapping("runninggroups/{runningGroupId}")
    public Iterable<RunningGroup> deleteRunningGroup(@PathVariable Long runningGroupId) {
        runningGroupRepository.deleteById(runningGroupId);
        return runningGroupRepository.findAll();
    }
}
