package app.runfinder.web.controllers;

import app.runfinder.domain.entities.RunGroupSignUp;
import app.runfinder.domain.repositories.RunGroupRepository;
import app.runfinder.domain.repositories.RunGroupSignUpRepository;
import app.runfinder.web.services.AppUserService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RunGroupSignUpController {

    private final RunGroupRepository runGroupRepository;
    private final RunGroupSignUpRepository runGroupSignUpRepository;
    private final AppUserService appUserService;

    public RunGroupSignUpController(RunGroupRepository runGroupRepository,
            RunGroupSignUpRepository runGroupSignUpRepository, AppUserService appUserService) {
        this.runGroupRepository = runGroupRepository;
        this.runGroupSignUpRepository = runGroupSignUpRepository;
        this.appUserService = appUserService;
    }

    @GetMapping("/signuptogroup/{id}")
    public String saveNewRunGroupSignUp(@PathVariable("id") Long runGroupId) {
        RunGroupSignUp newRunGroupSignUp = new RunGroupSignUp();

        newRunGroupSignUp.setAppUser(appUserService.getAuthenticatedAppUser());
        newRunGroupSignUp.setRunGroup(runGroupRepository.findById(runGroupId).get());
        runGroupSignUpRepository.save(newRunGroupSignUp);

        return "redirect:../rungrouplist";
    }
}