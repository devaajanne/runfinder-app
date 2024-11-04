package app.runfinder.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.RunGroup;

public interface RunGroupRepository extends CrudRepository<RunGroup, Long> {

     @Query(value = "SELECT * FROM run_groups r WHERE r.deleted_at IS NULL AND r.run_start_date > NOW() AND (LOWER(r.run_group_name) LIKE CONCAT('%', LOWER(:searchParameter), '%') OR r.zipcode IN (SELECT zipcode from zipcodes z WHERE LOWER(z.city) LIKE CONCAT('%', LOWER(:searchParameter), '%')))", nativeQuery = true)
     List<RunGroup> findBySearchParameter(String searchParameter);
}