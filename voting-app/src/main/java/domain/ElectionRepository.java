package domain;

import java.util.List;

public interface ElectionRepository {
    List<Election> findAll();
    Election findById(String id);

    void vote(String id, Candidate candidate);
}
