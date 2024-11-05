package app.runfinder.web.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.entities.RunGroupSignUp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class RunGroupService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void clearAndSaveSignUps(RunGroup runGroup, Set<RunGroupSignUp> newSignUps) {

        if (runGroup.getRunGroupSignUps() == null) {
            runGroup.setRunGroupSignUps(new HashSet<>());
        }

        runGroup.getRunGroupSignUps().clear();
        entityManager.merge(runGroup);

        runGroup.getRunGroupSignUps().addAll(newSignUps);
        entityManager.merge(runGroup);
    }
}
