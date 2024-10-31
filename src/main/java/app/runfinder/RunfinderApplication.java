package app.runfinder;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import app.runfinder.domain.entities.Role;
import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.entities.Zipcode;
import app.runfinder.domain.entities.AppUser;
import app.runfinder.domain.repositories.RoleRepository;
import app.runfinder.domain.repositories.RunGroupRepository;
import app.runfinder.domain.repositories.ZipcodeRepository;
import app.runfinder.domain.repositories.AppUserRepository;

@SpringBootApplication
public class RunfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunfinderApplication.class, args);
	}

	// CommandLineRunner for adding test data to a runtime database
	// Used to test application
	@Bean
	public CommandLineRunner runfinderCLR(RunGroupRepository runGroupRepository,
			ZipcodeRepository zipcodeRepository, RoleRepository roleRepository, AppUserRepository appUserRepository) {
		return (args) -> {

			// Test data has mostly been generated with ChatGPT

			zipcodeRepository.save(new Zipcode("00100", "Helsinki"));
			zipcodeRepository.save(new Zipcode("00200", "Helsinki"));
			zipcodeRepository.save(new Zipcode("00300", "Helsinki"));
			zipcodeRepository.save(new Zipcode("00400", "Helsinki"));
			zipcodeRepository.save(new Zipcode("00500", "Helsinki"));
			zipcodeRepository.save(new Zipcode("00600", "Helsinki"));
			zipcodeRepository.save(new Zipcode("00700", "Helsinki"));
			zipcodeRepository.save(new Zipcode("00800", "Helsinki"));
			zipcodeRepository.save(new Zipcode("00900", "Helsinki"));
			zipcodeRepository.save(new Zipcode("02100", "Espoo"));
			zipcodeRepository.save(new Zipcode("02200", "Espoo"));
			zipcodeRepository.save(new Zipcode("02300", "Espoo"));
			zipcodeRepository.save(new Zipcode("02400", "Kirkkonummi"));
			zipcodeRepository.save(new Zipcode("02600", "Espoo"));
			zipcodeRepository.save(new Zipcode("03100", "Nummela"));
			zipcodeRepository.save(new Zipcode("04400", "Järvenpää"));
			zipcodeRepository.save(new Zipcode("04500", "Kellokoski"));
			zipcodeRepository.save(new Zipcode("04600", "Mäntsälä"));
			zipcodeRepository.save(new Zipcode("05800", "Hyvinkää"));
			zipcodeRepository.save(new Zipcode("06100", "Porvoo"));

			roleRepository.save(new Role("USER"));
			roleRepository.save(new Role("CONTRIBUTOR"));
			roleRepository.save(new Role("ADMIN"));

			appUserRepository.save(
					new AppUser(
							"user",
							"userFirstname",
							"userLastname",
							"user@runfinder.com",
							roleRepository.findByRole("USER"),
							"$2a$10$1KmOKZbxn09.ptn75m9ttOWn6X9YDkZZyQOwCURb3wgM6kwEnIcPy"));
			appUserRepository.save(
					new AppUser(
							"contributor",
							"contributorFirstname",
							"contributorLastName",
							"contributor@runfinder.com",
							roleRepository.findByRole("CONTRIBUTOR"),
							"$2a$10$HFh46mkLcOGwS1GJZtPXqutsOrFlYvyODeQKMobBzjLuvGnTiOr3u"));
			appUserRepository.save(
					new AppUser(
							"admin",
							"adminFirstname",
							"adminLastname",
							"admin@runfinder.com",
							roleRepository.findByRole("ADMIN"),
							"$2a$10$/X5g8wxjMXw1pIDnJq7cL.WqJbg.LKQltNP8wXYpXjy/1Ha16lpKq"));

			runGroupRepository.save(
					new RunGroup(
							"runGroup1",
							LocalDate.of(2024, 12, 30),
							LocalTime.of(12, 0, 0),
							"Juoksukatu 1",
							zipcodeRepository.findByZipcode("00100"),
							null, appUserRepository.findById(2L).get()));
			runGroupRepository.save(
					new RunGroup(
							"runGroup2",
							LocalDate.of(2024, 12, 30),
							LocalTime.of(12, 0, 0),
							"Juoksutie 2",
							zipcodeRepository.findByZipcode("02200"),
							null, appUserRepository.findById(2L).get()));
			runGroupRepository.save(
					new RunGroup(
							"runGroup3",
							LocalDate.of(2024, 12, 30),
							LocalTime.of(12, 0, 0),
							"Juoksukuja 3",
							zipcodeRepository.findByZipcode("02300"),
							null, appUserRepository.findById(2L).get()));
			runGroupRepository.save(
					new RunGroup(
							"runGroup4",
							LocalDate.of(2024, 12, 30),
							LocalTime.of(12, 0, 0),
							"Juoksupolku 4",
							zipcodeRepository.findByZipcode("04400"),
							null, appUserRepository.findById(2L).get()));
			runGroupRepository.save(
					new RunGroup(
							"runGroup5",
							LocalDate.of(2022, 12, 30),
							LocalTime.of(12, 0, 0),
							"Juoksurinne 5",
							zipcodeRepository.findByZipcode("05800"),
							null, appUserRepository.findById(2L).get()));
		};
	};
}
