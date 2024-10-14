package app.runfinder.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.repositories.RunGroupRepository;
import app.runfinder.domain.repositories.ZipcodeRepository;

@Controller
public class RunGroupController {

    private final RunGroupRepository runGroupRepository;
    private final ZipcodeRepository zipcodeRepository;

    public RunGroupController(RunGroupRepository runGroupRepository, ZipcodeRepository zipcodeRepository) {
        this.runGroupRepository = runGroupRepository;
        this.zipcodeRepository = zipcodeRepository;
    }

    @GetMapping("/rungrouplist")
    public String showRunGroupList(Model model) {
        model.addAttribute("rungroups", runGroupRepository.findAll());
        return "rungrouplist";
    }

    @GetMapping("/add")
    public String addRunGroup(Model model) {
        model.addAttribute("rungroup", new RunGroup());
        model.addAttribute("zipcodes", zipcodeRepository.findAll());
        return "addrungroup";
    }

    @GetMapping("/edit/{id}")
       public String editRunGroup(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rungroup", runGroupRepository.findById(id));
        model.addAttribute("zipcodes", zipcodeRepository.findAll());
        return "editrungroup";
    }

    @PostMapping("/save")
    public String saveRunGroup(@ModelAttribute("runGroup") RunGroup runGroup, Model model) {

        runGroupRepository.save(runGroup);
        return "redirect:rungrouplist";
    }
}
