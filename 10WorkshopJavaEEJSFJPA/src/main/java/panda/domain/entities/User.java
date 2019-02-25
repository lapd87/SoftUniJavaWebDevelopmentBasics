package panda.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

import static panda.constants.Constants.*;

@Entity
@Table(name = USER_ENTITIES)
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private Role role;
    private List<Package> packages;
    private List<Receipt> receipts;

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

    @Enumerated(EnumType.STRING)
    @Column(name = PARAMETER_ROLE, nullable = false)
    @NotNull
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany(targetEntity = Package.class,
            mappedBy = PARAMETER_RECIPIENT)
    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    @OneToMany(targetEntity = Receipt.class,
            mappedBy = PARAMETER_RECIPIENT)
    public List<Receipt> getReceipts() {
        return receipts;
    }

    public void setReceipts(List<Receipt> receipts) {
        this.receipts = receipts;
    }
}
