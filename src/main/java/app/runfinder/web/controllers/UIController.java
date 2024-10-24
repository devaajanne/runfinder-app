package app.runfinder.web.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.repositories.RunGroupRepository;
import app.runfinder.domain.entities.RunGroupSignUp;
import app.runfinder.domain.repositories.RunGroupSignUpRepository;

import app.runfinder.web.services.AppUserService;

@Controller
public class UIController {

    private final RunGroupRepository runGroupRepository;
    private final RunGroupSignUpRepository runGroupSignUpRepository;
    private final AppUserService appUserService;

    public UIController(RunGroupRepository runGroupRepository, RunGroupSignUpRepository runGroupSignUpRepository,
            AppUserService appUserService) {
        this.runGroupRepository = runGroupRepository;
        this.runGroupSignUpRepository = runGroupSignUpRepository;
        this.appUserService = appUserService;
    }

    @GetMapping("/home")
    public String showHomepage(Model model) {
        return "home";
    }

    @GetMapping("/upcomingrungroups")
    public String showUpcomingRunGroups(Model model) {
        List<RunGroup> runGroupList = new ArrayList<RunGroup>();
        runGroupRepository.findAll().forEach(runGroupList::add);

        List<RunGroup> upcomingRunGroups = runGroupList.stream()
                .filter(runGroup -> runGroup.getDeletedAt() == null
                        && runGroup.getRunStartDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());

        List<RunGroupSignUp> runGroupSignUpList = new ArrayList<RunGroupSignUp>();
        runGroupSignUpRepository.findAll().forEach(runGroupSignUpList::add);

        List<Long> runGroupIds = runGroupSignUpList.stream()
                .filter(runGroupSignUp -> runGroupSignUp.getAppUser().getAppUserId() == appUserService.getAuthenticatedAppUser().getAppUserId())
                .map(runGroupSignUp -> runGroupSignUp.getRunGroup().getRunGroupId())
                .collect(Collectors.toList());

        model.addAttribute("upcomingrungrouplist", upcomingRunGroups);
        model.addAttribute("rungroupids", runGroupIds);

        return "upcomingrungroups";
    }

    @GetMapping("/usersignups")
    public String showUserSignUps(Model model) {
        List<RunGroupSignUp> runGroupSignUps = new ArrayList<RunGroupSignUp>();
        runGroupSignUpRepository.findAll().forEach(runGroupSignUps::add);

        List<RunGroupSignUp> userSignUps = runGroupSignUps.stream()
                .filter(runGroupSignUp -> runGroupSignUp.getRunGroup().getDeletedAt() == null
                        && runGroupSignUp.getAppUser().getAppUserId() == appUserService.getAuthenticatedAppUser()
                                .getAppUserId())
                .collect(Collectors.toList());

        model.addAttribute("usersignups", userSignUps);

        return "usersignups";
    }

    @GetMapping("/userrungroups")
    @PreAuthorize("hasAnyAuthority('CONTRIBUTOR', 'ADMIN')")
    public String showUserRunGroups(Model model) {
        List<RunGroup> runGroupList = new ArrayList<RunGroup>();
        runGroupRepository.findAll().forEach(runGroupList::add);

        List<RunGroup> userRunGroups = runGroupList.stream()
                .filter(runGroup -> runGroup.getDeletedAt() == null
                        && runGroup.getRunStartDate().isAfter(LocalDate.now())
                        && runGroup.getAddedByAppUser() == appUserService.getAuthenticatedAppUser())
                .collect(Collectors.toList());

        model.addAttribute("userrungroups", userRunGroups);

        return "userrungroups";
    }
}