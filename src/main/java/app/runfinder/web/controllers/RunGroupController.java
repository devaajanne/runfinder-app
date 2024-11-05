package app.runfinder.web.controllers;

import java.util.Set;
import java.util.HashSet;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;

import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.entities.RunGroupSignUp;
import app.runfinder.domain.repositories.RunGroupRepository;
import app.runfinder.domain.repositories.RunGroupSignUpRepository;
import app.runfinder.domain.repositories.ZipcodeRepository;

import app.runfinder.web.services.AppUserService;
import app.runfinder.web.services.RunGroupService;

@Controller
public class RunGroupController {

    private final RunGroupRepository runGroupRepository;
    private final RunGroupSignUpRepository runGroupSignUpRepository;
    private final ZipcodeRepository zipcodeRepository;
    private final AppUserService appUserService;
    private final RunGroupService runGroupService;

    public RunGroupController(RunGroupRepository runGroupRepository, RunGroupSignUpRepository runGroupSignUpRepository,
            ZipcodeRepository zipcodeRepository,
            AppUserService appUserService, RunGroupService runGroupService) {
        this.runGroupRepository = runGroupRepository;
        this.runGroupSignUpRepository = runGroupSignUpRepository;
        this.zipcodeRepository = zipcodeRepository;
        this.appUserService = appUserService;
        this.runGroupService = runGroupService;
    }

    @GetMapping("/addnewgroup")
    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'ADMIN')")
    public String addRunGroup(@RequestParam("origin") String origin, Model model) {
        RunGroup newRunGroup = new RunGroup();

        model.addAttribute("origin", origin);
        model.addAttribute("rungroup", newRunGroup);
        model.addAttribute("zipcodes", zipcodeRepository.findAll());
        return "addrungroup";
    }

    @PostMapping("/savenewgroup")
    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'ADMIN')")
    public String saveNewRunGroup(@Valid @ModelAttribute("rungroup") RunGroup runGroup, BindingResult bindingResult,
            @RequestParam("origin") String origin, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("origin", origin);
            model.addAttribute("rungroup", runGroup);
            model.addAttribute("zipcodes", zipcodeRepository.findAll());
            return "addrungroup";
        }

        runGroup.setAddedByAppUser(appUserService.getAuthenticatedAppUser());
        runGroupRepository.save(runGroup);

        if (origin.equals("userrungroups")) {
            return "redirect:/userrungroups";
        } else if (origin.equals("allrungroups")) {
            return "redirect:/allrungroups";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/editgroup/{id}")
    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'ADMIN')")
    public String editRunGroup(@PathVariable("id") Long id, @RequestParam("origin") String origin, Model model) {
        model.addAttribute("origin", origin);
        model.addAttribute("rungroup", runGroupRepository.findById(id));
        model.addAttribute("zipcodes", zipcodeRepository.findAll());
        return "editrungroup";
    }

    @PostMapping("/saveeditedgroup")
    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'ADMIN')")
    public String saveEditedRunGroup(@Valid @ModelAttribute("rungroup") RunGroup runGroup, BindingResult bindingResult,
            @RequestParam("origin") String origin, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("origin", origin);
            model.addAttribute("rungroup", runGroup);
            model.addAttribute("zipcodes", zipcodeRepository.findAll());
            return "editrungroup";
        }

        Set<RunGroupSignUp> newSignUps = new HashSet<>(runGroupSignUpRepository.findByRunGroup(runGroup));
        runGroupService.clearAndSaveSignUps(runGroup, newSignUps);

        if (origin.equals("userrungroups")) {
            return "redirect:/userrungroups";
        } else if (origin.equals("allrungroups")) {
            return "redirect:/allrungroups";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/deletegroup/{id}")
    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'ADMIN')")
    public String deleteRunGroup(@RequestParam("origin") String origin, @PathVariable("id") Long id,
            Model model) {
        RunGroup runGroup = runGroupRepository.findById(id).get();
        runGroup.delete();
        runGroupRepository.save(runGroup);

        if (origin.equals("userrungroups")) {
            return "redirect:/userrungroups";
        } else if (origin.equals("allrungroups")) {
            return "redirect:/allrungroups";
        } else {
            return "redirect:/home";
        }

    }

    @GetMapping("/restoregroup/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String restoreRunGroup(@PathVariable("id") Long id, Model model) {
        RunGroup runGroup = runGroupRepository.findById(id).get();
        runGroup.restore();
        runGroupRepository.save(runGroup);
        return "redirect:/allrungroups";
    }

    @GetMapping("/permanentlydeletegroup/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String permanentlyDeleteRunGroup(@PathVariable("id") Long id, Model model) {

        runGroupRepository.deleteById(id);

        return "redirect:/allrungroups";
    }
}
