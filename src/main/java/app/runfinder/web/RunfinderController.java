package app.runfinder.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import app.runfinder.domain.RunningGroupRepository;

import org.springframework.ui.Model;

@Controller
public class RunfinderController {

    @Autowired
    private RunningGroupRepository runningGroupRepository;

    @GetMapping("/runninggroups")
    public String showRunningGroups(Model model) {
        model.addAttribute("runningGroups", runningGroupRepository.findAll());
        return "runningGroups";
    }
}
