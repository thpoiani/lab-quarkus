package infrastructure.repositories.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "elections")
public class Election {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Election fromDomain(domain.Election domain) {
        Election entity = new Election();

        entity.setId(domain.id());

        return entity;
    }
}
