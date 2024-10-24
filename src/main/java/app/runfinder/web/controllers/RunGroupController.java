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

import app.runfinder.web.services.AppUserService;

@Controller
public class RunGroupController {

    private final RunGroupRepository runGroupRepository;
    private final ZipcodeRepository zipcodeRepository;
    private final AppUserService appUserService;

    public RunGroupController(RunGroupRepository runGroupRepository, ZipcodeRepository zipcodeRepository,
            AppUserService appUserService) {
        this.runGroupRepository = runGroupRepository;
        this.zipcodeRepository = zipcodeRepository;
        this.appUserService = appUserService;
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
        RunGroup newRunGroup = new RunGroup();
        newRunGroup.setAddedByAppUser(appUserService.getAuthenticatedAppUser());

        model.addAttribute("rungroup", newRunGroup);
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
        return "redirect:userrungroups";
    }

    @GetMapping("/editgroup/{id}")
    public String editRunGroup(@PathVariable("id") Long id, Model model) {
        model.addAttribute("rungroup", runGroupRepository.findById(id));
        model.addAttribute("zipcodes", zipcodeRepository.findAll());
        return "editrungroup";
    }

    @PostMapping("/saveeditedgroup")
    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'ADMIN')")
    public String saveEditedRunGroup(@Valid @ModelAttribute("rungroup") RunGroup runGroup, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("rungroup", runGroup);
            model.addAttribute("zipcodes", zipcodeRepository.findAll());
            return "editrungroup";
        }

        runGroupRepository.save(runGroup);
        return "redirect:userrungroups";
    }

    @GetMapping("/deletegroup/{id}")
    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'ADMIN')")
    public String deleteRunGroup(@PathVariable("id") Long id, Model model) {
        RunGroup runGroup = runGroupRepository.findById(id).get();
        runGroup.delete();
        runGroupRepository.save(runGroup);
        return "redirect:/userrungroups";
    }
}
