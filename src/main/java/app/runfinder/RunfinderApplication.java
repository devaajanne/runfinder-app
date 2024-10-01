package app.runfinder;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import app.runfinder.domain.Zipcode;
import app.runfinder.domain.ZipcodeRepository;
import app.runfinder.domain.RunningGroup;
import app.runfinder.domain.RunningGroupRepository;
import app.runfinder.domain.Role;
import app.runfinder.domain.RoleRepository;

@SpringBootApplication
public class RunfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunfinderApplication.class, args);
	}

	@Bean
	public CommandLineRunner runfinderCLR(RunningGroupRepository runningGroupRepository,
			ZipcodeRepository zipcodeRepository, RoleRepository roleRepository) {
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

			runningGroupRepository.save(new RunningGroup("runningGroup1", LocalDateTime.of(2024, 10, 1, 12, 00, 00),
					Duration.ofHours(1), "Juoksukatu 1", zipcodeRepository.findByZipcodeId("00100")));
			runningGroupRepository.save(new RunningGroup("runningGroup2", LocalDateTime.of(2024, 10, 3, 18, 30, 00),
					Duration.ofMinutes(90), "Juoksutie 2", zipcodeRepository.findByZipcodeId("02200")));
			runningGroupRepository.save(new RunningGroup("runningGroup3", LocalDateTime.of(2024, 10, 5, 7, 15, 00),
					Duration.ofHours(2), "Juoksukuja 3", zipcodeRepository.findByZipcodeId("02300")));
			runningGroupRepository.save(new RunningGroup("runningGroup4", LocalDateTime.of(2024, 10, 7, 16, 00, 00),
					Duration.ofMinutes(75), "Juoksupolku 4", zipcodeRepository.findByZipcodeId("04400")));
			runningGroupRepository.save(new RunningGroup("runningGroup5", LocalDateTime.of(2024, 10, 9, 9, 45, 00),
					Duration.ofHours(1).plusMinutes(30), "Juoksurinne 5", zipcodeRepository.findByZipcodeId("05800")));

			roleRepository.save(new Role("user"));
			roleRepository.save(new Role("contributor"));
			roleRepository.save(new Role("admin"));
		};
	};

}
