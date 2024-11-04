package app.runfinder.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.RunGroup;

public interface RunGroupRepository extends CrudRepository<RunGroup, Long> {

     @Query(value = "SELECT * FROM run_groups r WHERE LOWER(r.run_group_name) LIKE CONCAT('%', LOWER(:searchParameter), '%') OR r.zipcode IN (SELECT zipcode from zipcodes z WHERE LOWER(z.city) LIKE CONCAT('%', LOWER(:searchParameter), '%'))", nativeQuery = true)
     List<RunGroup> findBySearchParameter(String searchParameter);
}