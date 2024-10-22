package app.runfinder.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

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

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/rungrouplist")
    public String showRunGroupList(Model model) {
        model.addAttribute("rungroups", runGroupRepository.findAll());
        return "rungrouplist";
    }

    @GetMapping("/addnewgroup")
    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'ADMIN')")
    public String addRunGroup(Model model) {
        model.addAttribute("rungroup", new RunGroup());
        model.addAttribute("zipcodes", zipcodeRepository.findAll());
        return "addrungroup";
    }

    @PostMapping("/savenewgroup")
    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'ADMIN')")
    public String saveNewRunGroup(@Valid @ModelAttribute("rungroup") RunGroup runGroup, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rungroup", runGroup);
            model.addAttribute("zipcodes", zipcodeRepository.findAll());
            return "addrungroup";
        }

        runGroupRepository.save(runGroup);
        return "redirect:rungrouplist";
    }

    @GetMapping("/editgroup/{id}")
    public String editRunGroup(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rungroup", runGroupRepository.findById(id));
        model.addAttribute("zipcodes", zipcodeRepository.findAll());
        return "editrungroup";
    }

    @PostMapping("/saveeditedgroup")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveEditedRunGroup(@Valid @ModelAttribute("rungroup") RunGroup runGroup, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rungroup", runGroup);
            model.addAttribute("zipcodes", zipcodeRepository.findAll());
            return "editrungroup";
        }

        runGroupRepository.save(runGroup);
        return "redirect:rungrouplist";
    }

    @GetMapping("/deletegroup/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteRunGroup(@PathVariable("id") Long id, Model model) {
        RunGroup runGroup = runGroupRepository.findById(id).get();
        runGroup.delete();
        runGroupRepository.save(runGroup);
        return "redirect:/rungrouplist";
    }
}
