package app.runfinder.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.Zipcode;

public interface ZipcodeRepository extends CrudRepository<Zipcode, String> {
    // Added an additional CRUD method to get a specific zipcode
    Zipcode findByZipcode(String zipcode);
}