package app.runfinder.web.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import app.runfinder.domain.classes.SignUpForm;
import app.runfinder.domain.entities.AppUser;
import app.runfinder.domain.repositories.RoleRepository;
import app.runfinder.domain.repositories.AppUserRepository;

@Controller
public class UserController {

    private final RoleRepository roleRepository;
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(RoleRepository roleRepository, AppUserRepository appUserRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/signup")
    public String addAppUser(Model model) {
        model.addAttribute("signupform", new SignUpForm());
        return "signup";
    }

    @PostMapping("/savenewuser")
    public String saveNewUser(@Valid @ModelAttribute("signupform") SignUpForm signUpForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (signUpForm.getPassword().equals(signUpForm.getPasswordCheck())) {
                String pw = signUpForm.getPassword();
                String hashPassword = passwordEncoder.encode(pw);

                AppUser newUser = new AppUser();
                newUser.setUsername(signUpForm.getUsername());
                newUser.setFirstName(signUpForm.getFirstName());
                newUser.setLastName(signUpForm.getLastName());
                newUser.setEmail(signUpForm.getEmail());
                newUser.setRole(roleRepository.findByRole("USER"));
                newUser.setPassword(hashPassword);

                if (appUserRepository.findByUsername(signUpForm.getUsername()) == null) {
                    appUserRepository.save(newUser);
                } else {
                    bindingResult.rejectValue("username", "err.username", "Username already exists");
                    return "signup";
                }
            } else {
                bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords do not match");
                return "signup";
            }
        } else {
            return "signup";
        }

        return "redirect:/login";
    }

    @GetMapping("/editappuserrole/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editRole(@PathVariable("id") Long id, Model model) {
        model.addAttribute("appuser", appUserRepository.findById(id).get());
        model.addAttribute("roles", roleRepository.findAll());

        return "editappuserrole";
    }

    @PostMapping("/saveeditedappuserrole")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveEditedRole(@Valid @ModelAttribute("appuser") AppUser appUser) {

        appUserRepository.save(appUser);
        
        return "redirect:/allappusers";
    }
}