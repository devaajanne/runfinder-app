package app.runfinder.web.services;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.runfinder.domain.entities.AppUser;
import app.runfinder.domain.repositories.AppUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public UserDetailServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currentAppUser = appUserRepository.findByUsername(username);
        UserDetails appUser = new org.springframework.security.core.userdetails.User(username,
                currentAppUser.getPassword(), AuthorityUtils.createAuthorityList(currentAppUser.getRole().getRole()));

        return appUser;
    }
}
