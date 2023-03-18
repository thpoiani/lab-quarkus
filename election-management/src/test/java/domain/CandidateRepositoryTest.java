package domain;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class CandidateRepositoryTest {
    public abstract CandidateRepository repository();

    @Test
    void save() {
        Candidate candidate = Instancio.create(Candidate.class);
        repository().save(candidate);

        Optional<Candidate> result = repository().findById(candidate.id());

        assertTrue(result.isPresent());
        assertEquals(candidate, result.get());
    }

    @Test
    void findAll() {
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();

        repository().save(candidates);

        List<Candidate> result = repository().findAll();

        assertEquals(result.size(), candidates.size());
    }

    @Test
    void findByName() {
        Candidate candidate1 = Instancio.create(Candidate.class);
        Candidate candidate2 = Instancio.of(Candidate.class)
                .set(field("familyName"), "Poiani").create();

        CandidateQuery query = new CandidateQuery.Builder().name("POI").build();

        repository().save(List.of(candidate1, candidate2));

        List<Candidate> result = repository().find(query);

        assertEquals(1, result.size());
        assertEquals(candidate2, result.get(0));
    }
}