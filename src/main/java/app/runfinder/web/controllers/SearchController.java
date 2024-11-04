package app.runfinder.web.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.entities.RunGroupSignUp;
import app.runfinder.domain.repositories.RunGroupRepository;
import app.runfinder.domain.repositories.RunGroupSignUpRepository;

import app.runfinder.web.services.AppUserService;

@Controller
public class SearchController {
    private final RunGroupRepository runGroupRepository;
    private final RunGroupSignUpRepository runGroupSignUpRepository;
    private final AppUserService appUserService;

    public SearchController(RunGroupRepository runGroupRepository, RunGroupSignUpRepository runGroupSignUpRepository,
            AppUserService appUserService) {
        this.runGroupRepository = runGroupRepository;
        this.runGroupSignUpRepository = runGroupSignUpRepository;
        this.appUserService = appUserService;
    }

    @GetMapping("/searchresults")
    public String getSearchResults(@RequestParam("searchparameter") String searchParameter, Model model) {
        List<RunGroup> searchResultsList = new ArrayList<RunGroup>();
        runGroupRepository.findBySearchParameter(searchParameter).forEach(searchResultsList::add);

        List<RunGroupSignUp> runGroupSignUpList = new ArrayList<RunGroupSignUp>();
        runGroupSignUpRepository.findAll().forEach(runGroupSignUpList::add);

        List<Long> runGroupIds = runGroupSignUpList.stream()
                .filter(runGroupSignUp -> runGroupSignUp.getAppUser().getAppUserId() == appUserService
                        .getAuthenticatedAppUser().getAppUserId())
                .map(runGroupSignUp -> runGroupSignUp.getRunGroup().getRunGroupId())
                .collect(Collectors.toList());

        model.addAttribute("searchparameter", searchParameter);
        model.addAttribute("searchresultslist", searchResultsList);
        model.addAttribute("rungroupids", runGroupIds);

        return "searchresults";
    }
}