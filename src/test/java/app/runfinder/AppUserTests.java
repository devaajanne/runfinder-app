package app.runfinder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

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
                        "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy",
                        null));

        assertThat(appUserRepository.count()).isEqualTo(appUserCount + 1);
    }

    @Test
    public void findAppUserByIdTest() {

        appUserRepository.save(new AppUser(
                "testUser",
                "userFirstname",
                "userLastname",
                "user@runfinder.com",
                roleRepository.findByRole("USER"),
                "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy",
                null));

        long appUserId = appUserRepository.findByUsername("testUser").getAppUserId();

        assertThat(appUserRepository.findById(appUserId).get().getFirstName()).isEqualTo("userFirstname");
    }

    @Test
    public void findAppUserByUsername() {

        appUserRepository.save(new AppUser(
                "testUser",
                "userFirstname",
                "userLastname",
                "user@runfinder.com",
                roleRepository.findByRole("USER"),
                "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy",
                null));

        AppUser appUser = appUserRepository.findByUsername("testUser");

        assertThat(appUserRepository.findById(appUser.getAppUserId()).get().getFirstName()).isEqualTo("userFirstname");
    }

    @Test
    public void deleteAppUserTest() {

        appUserRepository.save(new AppUser(
                "testUser",
                "userFirstname",
                "userLastname",
                "user@runfinder.com",
                roleRepository.findByRole("USER"),
                "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy",
                null));

        long appUserId = appUserRepository.findByUsername("testUser").getAppUserId();

        AppUser appUser = appUserRepository.findById(appUserId).get();

        appUser.delete();

        assertThat(appUser.getDeletedAt()).isNotNull();
    }

    @Test
    public void restoreAppUserTest() {

        appUserRepository.save(new AppUser(
                "testUser",
                "userFirstname",
                "userLastname",
                "user@runfinder.com",
                roleRepository.findByRole("USER"),
                "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy",
                null));

        long appUserId = appUserRepository.findByUsername("testUser").getAppUserId();

        AppUser appUser = appUserRepository.findById(appUserId).get();

        appUser.delete();
        appUser.restore();

        assertThat(appUser.getDeletedAt()).isNull();
    }
}
