package api.dto.in;

import domain.Candidate;

import java.util.Optional;

public record UpdateCandidate(Optional<String> photo,
                              String givenName,
                              String familyName,
                              String email,
                              Optional<String> phone,
                              Optional<String> jobTitle) {
    public Candidate toDomain(String id) {
        return new Candidate(id, photo(), givenName(), familyName(), email(), phone(), jobTitle());
    }
}
