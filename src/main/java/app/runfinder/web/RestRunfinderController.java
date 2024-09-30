package app.runfinder.web;

import org.springframework.web.bind.annotation.RestController;

import app.runfinder.domain.RunningGroup;
import app.runfinder.domain.RunningGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}