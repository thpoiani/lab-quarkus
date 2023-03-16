package domain;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;

@ApplicationScoped
public class ElectionService {
    private final Instance<ElectionRepository> repositories;
    private final CandidateService candidateService;

    public ElectionService(Instance<ElectionRepository> repositories, CandidateService candidateService) {
        this.repositories = repositories;
        this.candidateService = candidateService;
    }

    public void submit() {
        Election election = Election.create(candidateService.findAll());
        repositories.forEach(repository -> repository.submit(election));
    }
}