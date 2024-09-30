package app.runfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class RunfinderController {

    @GetMapping("/runninggroups")
    public String showRunningGroups(Model model) {
        model.addAttribute("runningGroups", runningGroupRepository.findall());
        return "runningGroups";
    }
}
