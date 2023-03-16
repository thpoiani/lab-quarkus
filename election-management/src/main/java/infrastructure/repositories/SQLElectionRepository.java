package infrastructure.repositories;

import domain.Election;
import domain.ElectionRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class SQLElectionRepository implements ElectionRepository {
    private final EntityManager entityManager;

    public SQLElectionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void submit(Election election) {
        entityManager.merge(infrastructure.repositories.entities.Election.fromDomain(election));

        election.votes()
                .entrySet()
                .stream()
                .map(entry -> infrastructure.repositories.entities.ElectionCandidate.fromDomain(election, entry.getKey(), entry.getValue()))
                .forEach(entityManager::merge);
    }
}
