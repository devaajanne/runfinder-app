package app.runfinder.web.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.runfinder.domain.entities.Zipcode;
import app.runfinder.domain.repositories.ZipcodeRepository;

@RestController
@RequestMapping("/api")
public class RestZipcodeController {

    @Autowired
    ZipcodeRepository zipcodeRepository;

    @GetMapping("/zipcodes")
    public Iterable<Zipcode> getZipcodes() {
        return zipcodeRepository.findAll();
    }

    @GetMapping("/zipcodes/{zipcode}")
    public Zipcode getZipcodes(@PathVariable String zipcode) {
        return zipcodeRepository.findByZipcode(zipcode);
    }

}
