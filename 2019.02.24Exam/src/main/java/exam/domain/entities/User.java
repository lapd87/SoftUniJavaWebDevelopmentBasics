package exam.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static exam.constants.Constants.*;

@Entity
@Table(name = USER_ENTITIES)
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;

    public User() {
    }

    @Column(name = PARAMETER_USERNAME, nullable = false, unique = true, updatable = false)
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = PARAMETER_PASSWORD, nullable = false)
    @NotNull
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = PARAMETER_EMAIL, nullable = false)
    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
