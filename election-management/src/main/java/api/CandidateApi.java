package api;

import api.dto.out.Candidate;
import domain.CandidateService;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class CandidateApi {
    private final CandidateService service;

    public CandidateApi(CandidateService service) {
        this.service = service;
    }

    public void create(api.dto.in.CreateCandidate dto) {
        service.save(dto.toDomain());
    }

    public api.dto.out.Candidate update(String id, api.dto.in.UpdateCandidate dto) {
        service.save(dto.toDomain(id));
        return api.dto.out.Candidate.fromDomain(service.findById(id));
    }

    public List<Candidate> list() {
        return service.findAll().stream().map(Candidate::fromDomain).toList();
    }
}
