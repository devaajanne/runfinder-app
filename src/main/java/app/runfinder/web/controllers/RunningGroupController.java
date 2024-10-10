package app.runfinder.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.runfinder.domain.repositories.RunGroupRepository;



@Controller
public class RunningGroupController {

    private final RunGroupRepository runningGroupRepository;

    public RunningGroupController(RunGroupRepository runningGroupRepository) {
        this.runningGroupRepository = runningGroupRepository;
    }

    @GetMapping("/runninggroups")
    public String showRunningGroups(Model model) {
        model.addAttribute("runninggroups", runningGroupRepository.findAll());
        return "runninggroups";
    }

}
