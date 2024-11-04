package app.runfinder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.repositories.AppUserRepository;
import app.runfinder.domain.repositories.ZipcodeRepository;
import app.runfinder.domain.repositories.RunGroupRepository;

@DataJpaTest
 @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RunGroupTests {

    @Autowired
    private RunGroupRepository runGroupRepository;

    @Autowired
    private ZipcodeRepository zipcodeRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    public void addNewRunGroupTest() {
        long runGroupCount = runGroupRepository.count();

        runGroupRepository.save(new RunGroup(
                "runGroup",
                LocalDate.of(2024, 12, 30),
                LocalTime.of(12, 0, 0),
                "Juoksutie 2",
                zipcodeRepository.findByZipcode("00100"),
                null,
                appUserRepository.findById(1L).get()));

        assertThat(runGroupRepository.count()).isEqualTo(runGroupCount + 1);
    }

    @Test
    public void findRunGroupByIdTest() {
        runGroupRepository.save(new RunGroup(
                "runGroup",
                LocalDate.of(2024, 12, 30),
                LocalTime.of(12, 0, 0),
                "Juoksutie 2",
                zipcodeRepository.findByZipcode("00100"),
                null,
                appUserRepository.findById(1L).get()));

        Optional<RunGroup> optionalRunGroup = runGroupRepository.findById(1L);
        RunGroup runGroup = optionalRunGroup.get();

        assertThat(runGroup.getRunGroupId()).isEqualTo(1L);
    }

    @Test
    public void deleteRunGroupTest() {
        runGroupRepository.save(new RunGroup(
                "runGroup",
                LocalDate.of(2024, 12, 30),
                LocalTime.of(12, 0, 0),
                "Juoksutie 2",
                zipcodeRepository.findByZipcode("00100"),
                null,
                appUserRepository.findById(1L).get()));

        Optional<RunGroup> optionalRunGroup = runGroupRepository.findById(1L);
        RunGroup runGroup = optionalRunGroup.get();

        runGroup.delete();

        assertThat(runGroup.getDeletedAt()).isNotNull();
    }

    @Test
    public void restoreRunGroupTest() {
        runGroupRepository.save(new RunGroup(
                "runGroup",
                LocalDate.of(2024, 12, 30),
                LocalTime.of(12, 0, 0),
                "Juoksutie 2",
                zipcodeRepository.findByZipcode("00100"),
                null,
                appUserRepository.findById(1L).get()));

        Optional<RunGroup> optionalRunGroup = runGroupRepository.findById(1L);
        RunGroup runGroup = optionalRunGroup.get();

        runGroup.delete();
        runGroup.restore();

        assertThat(runGroup.getDeletedAt()).isNull();
    }

}
