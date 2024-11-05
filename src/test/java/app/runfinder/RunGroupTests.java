package app.runfinder;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import app.runfinder.domain.entities.AppUser;
import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.repositories.AppUserRepository;
import app.runfinder.domain.repositories.ZipcodeRepository;
import app.runfinder.domain.repositories.RunGroupRepository;
import app.runfinder.domain.repositories.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RunGroupTests {

    @Autowired
    private RunGroupRepository runGroupRepository;

    @Autowired
    private ZipcodeRepository zipcodeRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void addNewRunGroupTest() {
        long runGroupCount = runGroupRepository.count();

        appUserRepository.save(new AppUser(
                "testUser",
                "userFirstname",
                "userLastname",
                "user@runfinder.com",
                roleRepository.findByRole("USER"),
                "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy",
                null));

        runGroupRepository.save(new RunGroup(
                "runGroup",
                LocalDate.of(2024, 12, 30),
                LocalTime.of(12, 0, 0),
                "Juoksutie 2",
                zipcodeRepository.findByZipcode("00100"),
                null,
                appUserRepository.findByUsername("testUser")));

        assertThat(runGroupRepository.count()).isEqualTo(runGroupCount + 1);
    }

    @Test
    public void findRunGroupByIdTest() {

        appUserRepository.save(new AppUser(
                "testUser",
                "userFirstname",
                "userLastname",
                "user@runfinder.com",
                roleRepository.findByRole("USER"),
                "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy",
                null));

        runGroupRepository.save(new RunGroup(
                "runGroup",
                LocalDate.of(2024, 12, 30),
                LocalTime.of(12, 0, 0),
                "Juoksutie 2",
                zipcodeRepository.findByZipcode("00100"),
                null,
                appUserRepository.findByUsername("testUser")));

        long runGroupId = runGroupRepository.findByRunGroupName("runGroup").getRunGroupId();

        assertThat(runGroupRepository.findById(runGroupId).get().getStartAddress()).isEqualTo("Juoksutie 2");
    }

    @Test
    public void findRunGroupByGroupNameTest() {

        appUserRepository.save(new AppUser(
                "testUser",
                "userFirstname",
                "userLastname",
                "user@runfinder.com",
                roleRepository.findByRole("USER"),
                "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy",
                null));

        runGroupRepository.save(new RunGroup(
                "runGroup",
                LocalDate.of(2024, 12, 30),
                LocalTime.of(12, 0, 0),
                "Juoksutie 2",
                zipcodeRepository.findByZipcode("00100"),
                null,
                appUserRepository.findByUsername("testUser")));

        RunGroup runGroup = runGroupRepository.findByRunGroupName("runGroup");

        assertThat(runGroupRepository.findById(runGroup.getRunGroupId()).get().getStartAddress())
                .isEqualTo("Juoksutie 2");

    }

    @Test
    public void deleteRunGroupTest() {

        appUserRepository.save(new AppUser(
                "testUser",
                "userFirstname",
                "userLastname",
                "user@runfinder.com",
                roleRepository.findByRole("USER"),
                "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy",
                null));

        runGroupRepository.save(new RunGroup(
                "runGroup",
                LocalDate.of(2024, 12, 30),
                LocalTime.of(12, 0, 0),
                "Juoksutie 2",
                zipcodeRepository.findByZipcode("00100"),
                null,
                appUserRepository.findByUsername("testUser")));

        RunGroup runGroup = runGroupRepository.findByRunGroupName("runGroup");

        runGroup.delete();

        assertThat(runGroup.getDeletedAt()).isNotNull();
    }

    @Test
    public void restoreRunGroupTest() {

        appUserRepository.save(new AppUser(
                "testUser",
                "userFirstname",
                "userLastname",
                "user@runfinder.com",
                roleRepository.findByRole("USER"),
                "$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy",
                null));

        runGroupRepository.save(new RunGroup(
                "runGroup",
                LocalDate.of(2024, 12, 30),
                LocalTime.of(12, 0, 0),
                "Juoksutie 2",
                zipcodeRepository.findByZipcode("00100"),
                null,
                appUserRepository.findByUsername("testUser")));

        long runGroupId = runGroupRepository.findByRunGroupName("runGroup").getRunGroupId();

        RunGroup runGroup = runGroupRepository.findById(runGroupId).get();

        runGroup.delete();
        runGroup.restore();

        assertThat(runGroup.getDeletedAt()).isNull();
    }
}
