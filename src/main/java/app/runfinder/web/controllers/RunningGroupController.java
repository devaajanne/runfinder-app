package app.runfinder.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.runfinder.domain.repositories.RunningGroupRepository;



@Controller
public class RunningGroupController {

    private final RunningGroupRepository runningGroupRepository;

    public RunningGroupController(RunningGroupRepository runningGroupRepository) {
        this.runningGroupRepository = runningGroupRepository;
    }

    @GetMapping("/runninggroups")
    public String showRunningGroups(Model model) {
        model.addAttribute("runninggroups", runningGroupRepository.findAll());
        return "runninggroups";
    }

}
