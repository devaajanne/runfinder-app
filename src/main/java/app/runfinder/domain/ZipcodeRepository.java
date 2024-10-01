package app.runfinder.domain;

import org.springframework.data.repository.CrudRepository;

public interface ZipcodeRepository extends CrudRepository<Zipcode, String> {
    // Added an additional CRUD method to get a specific zipcode
    Zipcode findByZipcodeId(String zipcodeId);
}