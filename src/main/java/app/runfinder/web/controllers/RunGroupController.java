package app.runfinder.web.controllers;

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

        runGroupRepository.save(runGroup);

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
