package app.runfinder.domain;

import org.springframework.data.repository.CrudRepository;

public interface ZipcodeRepository extends CrudRepository<Zipcode, String> {
    Zipcode findByZipcodeId(String zipcodeId);
}