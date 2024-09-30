package app.runfinder;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import app.runfinder.domain.RunningGroup;
import app.runfinder.domain.RunningGroupRepository;
import app.runfinder.domain.Zipcode;
import app.runfinder.domain.ZipcodeRepository;

@SpringBootApplication
public class RunfinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RunfinderApplication.class, args);
	}

	@Bean
	public CommandLineRunner runfinderCLR(RunningGroupRepository runningGroupRepository,
			ZipcodeRepository zipcodeRepository) {
		return (args) -> {

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

			runningGroupRepository.save(new RunningGroup("runningGroup1", LocalDateTime.of(2024, 10, 1, 12, 00, 00), Duration.ofHours(1), "Juoksukatu 1", zipcodeRepository.findByZipcodeId("00100")));
		};
	};

}
