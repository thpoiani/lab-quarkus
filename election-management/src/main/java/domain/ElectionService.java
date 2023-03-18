package domain;

import domain.annotations.Principal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import java.util.List;

@ApplicationScoped
public class ElectionService {
    private final CandidateService candidateService;
    private final Instance<ElectionRepository> repositories;
    private final ElectionRepository repository;

    public ElectionService(CandidateService candidateService,
                           @Any Instance<ElectionRepository> repositories,
                           @Principal ElectionRepository repository) {
        this.candidateService = candidateService;
        this.repositories = repositories;
        this.repository = repository;
    }

    public List<Election> findAll() {
        return repository.findAll();
    }

    public void submit() {
        Election election = Election.create(candidateService.findAll());
        repositories.forEach(repository -> repository.submit(election));
    }
}
