package app.runfinder.web.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import app.runfinder.domain.entities.RunGroup;
import app.runfinder.domain.entities.RunGroupSignUp;
import app.runfinder.domain.repositories.RunGroupRepository;
import app.runfinder.domain.repositories.RunGroupSignUpRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class RunGroupService {

    private final RunGroupRepository runGroupRepository;
    private final RunGroupSignUpRepository runGroupSignUpRepository;

    public RunGroupService(RunGroupRepository runGroupRepository, RunGroupSignUpRepository runGroupSignUpRepository) {
        this.runGroupRepository = runGroupRepository;
        this.runGroupSignUpRepository = runGroupSignUpRepository;
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void clearAndSaveSignUps(RunGroup runGroup, Set<RunGroupSignUp> newSignUps) {

        if (runGroup.getRunGroupSignUps() == null) {
            runGroup.setRunGroupSignUps(new HashSet<>());  // Initialize if null
        }
        
        // Step 1: Clear the collection and save to remove orphans
        runGroup.getRunGroupSignUps().clear();
        entityManager.merge(runGroup); // Save to flush orphan removals

        // Step 2: Add new elements and save again
        runGroup.getRunGroupSignUps().addAll(newSignUps);
        entityManager.merge(runGroup);
    }
    
}
