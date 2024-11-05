package app.runfinder.domain.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.entities.RunGroupSignUp;

public interface RunGroupSignUpRepository extends CrudRepository<RunGroupSignUp, Long> {
     List<RunGroupSignUp> findByRunGroup(RunGroup runGroup);
}