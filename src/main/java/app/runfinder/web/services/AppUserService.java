package app.runfinder.web.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import app.runfinder.domain.entities.AppUser;
import app.runfinder.domain.repositories.AppUserRepository;

@Service
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser getAuthenticatedAppUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails appUserDetails = (UserDetails)authentication.getPrincipal();
        
        return appUserRepository.findByUsername(appUserDetails.getUsername());
    }
}
