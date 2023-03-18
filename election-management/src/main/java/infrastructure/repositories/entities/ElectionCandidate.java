package infrastructure.repositories.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity(name = "election_candidate")
public class ElectionCandidate {
    @EmbeddedId
    private ElectionCandidateId id;

    private Integer votes;

    public ElectionCandidateId getId() {
        return id;
    }

    public void setId(ElectionCandidateId id) {
        this.id = id;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public static ElectionCandidate fromDomain(domain.Election election, domain.Candidate candidate, Integer votes) {
        ElectionCandidate entity = new ElectionCandidate();

        ElectionCandidateId id = new ElectionCandidateId();
        id.setElectionId(election.id());
        id.setCandidateId(candidate.id());

        entity.setId(id);
        entity.setVotes(votes);

        return entity;
    }
}
