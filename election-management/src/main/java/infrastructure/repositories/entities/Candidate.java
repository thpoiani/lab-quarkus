package infrastructure.repositories.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;

@Entity(name = "candidates")
public class Candidate {
    @Id
    private String id;
    private String photo;
    @Column(name = "given_name")
    private String givenName;
    @Column(name = "family_name")
    private String familyName;
    private String email;
    private String phone;
    @Column(name = "job_title")
    private String jobTitle;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public static Candidate fromDomain(domain.Candidate domain) {
        Candidate entity = new Candidate();

        entity.setId(domain.id());
        entity.setPhoto(domain.photo().orElse(null));
        entity.setGivenName(domain.givenName());
        entity.setFamilyName(domain.familyName());
        entity.setEmail(domain.email());
        entity.setPhone(domain.phone().orElse(null));
        entity.setJobTitle(domain.jobTitle().orElse(null));

        return entity;
    }

    public domain.Candidate toDomain() {
        return new domain.Candidate(getId(),
                                    Optional.ofNullable(getPhoto()),
                                    getGivenName(),
                                    getFamilyName(),
                                    getEmail(),
                                    Optional.ofNullable(getPhone()),
                                    Optional.ofNullable(getJobTitle()));
    }
}
