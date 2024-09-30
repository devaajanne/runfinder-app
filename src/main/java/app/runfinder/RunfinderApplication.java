package app.runfinder;

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
		};
	};

}
