package infrastructure.repositories;

import domain.Candidate;
import domain.Election;
import domain.ElectionRepository;
import infrastructure.repositories.entities.ElectionCandidate;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

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
                .map(entry -> ElectionCandidate.fromDomain(election, entry.getKey(), entry.getValue()))
                .forEach(entityManager::merge);
    }

    public List<Election> findAll() {
        Stream<Object[]> stream = entityManager.createNativeQuery("SELECT e.id AS election_id, c.id AS candidate_id, c.photo, c.given_name, c.family_name, c.email, c.phone, c.job_title, ec.votes FROM elections AS e INNER JOIN election_candidate AS ec ON ec.election_id = e.id INNER JOIN candidates AS c ON ec.candidate_id = c.id")
                                               .getResultStream();

        Map<String, List<Object[]>> map = stream.collect(groupingBy(o -> o[0].toString()));

        return map.entrySet()
                  .stream()
                  .map(entry -> {
                      Map.Entry<Candidate, Integer>[] candidates = entry.getValue()
                                                                        .stream()
                                                                        .map(row -> Map.entry(new Candidate(row[1].toString(),
                                                                                                            Optional.ofNullable(row[2].toString()),
                                                                                                            row[3].toString(),
                                                                                                            row[4].toString(),
                                                                                                            row[5].toString(),
                                                                                                            Optional.ofNullable(row[6].toString()),
                                                                                                            Optional.ofNullable(row[7].toString())),
                                                                                              (Integer) row[8]))
                                                                        .toArray(Map.Entry[]::new);

                      return new Election(entry.getKey(), Map.ofEntries(candidates));
                  })
                  .toList();
    }

    @Transactional
    public void sync(Election election) {
        election.votes()
                .entrySet()
                .stream()
                .map(entry -> ElectionCandidate.fromDomain(election, entry.getKey(), entry.getValue()))
                .forEach(entityManager::merge);
    }
}
