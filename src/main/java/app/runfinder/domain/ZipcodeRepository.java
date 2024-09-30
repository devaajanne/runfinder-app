package app.runfinder.domain;

import org.springframework.data.repository.CrudRepository;

public interface ZipcodeRepository extends CrudRepository<Zipcode, String> {
    Zipcode findByZipcode(String zipcode);
}