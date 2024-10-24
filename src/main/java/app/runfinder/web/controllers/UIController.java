package app.runfinder.web.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.repositories.RunGroupRepository;

@Controller
public class UIController {

    private final RunGroupRepository runGroupRepository;

    public UIController(RunGroupRepository runGroupRepository) {
        this.runGroupRepository = runGroupRepository;
    }

    @GetMapping("/upcomingrungroups")
    public String showUpcomingRunGroups(Model model) {
        List<RunGroup> runGroups = new ArrayList<RunGroup>();
        runGroupRepository.findAll().forEach(runGroups::add);

        List<RunGroup> upcomingRunGroups = runGroups.stream()
                .filter(runGroup -> runGroup.getDeletedAt() == null
                        && runGroup.getRunStartDate().isAfter(LocalDate.now()))
                .collect(Collectors.toList());

        model.addAttribute("upcomingrungroups", upcomingRunGroups);

        return "upcomingrungrouplist";
    }
}