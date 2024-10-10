package app.runfinder.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.runfinder.domain.repositories.RunGroupRepository;

@Controller
public class RunGroupController {

    private final RunGroupRepository runGroupRepository;

    public RunGroupController(RunGroupRepository runGroupRepository) {
        this.runGroupRepository = runGroupRepository;
    }

    @GetMapping("/rungroups")
    public String showRunGroups(Model model) {
        model.addAttribute("rungroups", runGroupRepository.findAll());
        return "rungrouplist";
    }



}
