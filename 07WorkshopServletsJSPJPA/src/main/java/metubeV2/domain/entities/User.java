package metubeV2.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static metubeV2.constants.Constants.*;

@Entity
@Table(name = USER_ENTITIES)
public class User extends BaseEntity {

    private String username;
    private String email;
    private String password;
    private List<Tube> tubes;

    public User() {
    }

    @Column(name = PARAMETER_USER, nullable = false, unique = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = PARAMETER_EMAIL, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = PARAMETER_PASS, nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(targetEntity = Tube.class, mappedBy = PARAMETER_UPLOADER)
    public List<Tube> getTubes() {
        return tubes;
    }

    public void setTubes(List<Tube> tubes) {
        this.tubes = tubes;
    }
}
