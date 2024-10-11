package app.runfinder.web.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import app.runfinder.domain.entities.Zipcode;
import app.runfinder.domain.repositories.ZipcodeRepository;

@Controller
public class ZipcodeController {

    private final ZipcodeRepository zipcodeRepository;

    public ZipcodeController(ZipcodeRepository zipcodeRepository) {
        this.zipcodeRepository = zipcodeRepository;
    }

    @GetMapping("/zipcodes/{zipcode}")
    @ResponseBody
    public String getCityByZipCode(@PathVariable String zipcode) {
        Zipcode z = zipcodeRepository.findByZipcode(zipcode);
        return z.getCity();
    }
}
