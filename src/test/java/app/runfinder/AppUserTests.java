package app.runfinder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import app.runfinder.domain.entities.AppUser;
import app.runfinder.domain.repositories.RoleRepository;
import app.runfinder.domain.repositories.AppUserRepository;

@DataJpaTest
 @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppUserTests {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void addNewAppUserTest() {
        long appUserCount = appUserRepository.count();

        appUserRepository.save(
                new AppUser(
                        "testUser",
                        "userFirstname",
                        "userLastname",
                        "user@runfinder.com",
                        roleRepository.findByRole("USER"),
                        "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy"));

        assertThat(appUserRepository.count()).isEqualTo(appUserCount + 1);
    }

    @Test
    public void findAppUserByIdTest() {
        appUserRepository.save(
                new AppUser(
                        "testUser",
                        "userFirstname",
                        "userLastname",
                        "user@runfinder.com",
                        roleRepository.findByRole("USER"),
                        "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy"));

        Optional<AppUser> optionalAppUser = appUserRepository.findById(1L);
        AppUser appUser = optionalAppUser.get();

        assertThat(appUser.getAppUserId()).isEqualTo(1L);
    }
}
