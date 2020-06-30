package eu.itdc.internetprovider.persistence.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotBlank
    @Size(min = 5, max = 80)
    private String password;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    private boolean accountNonLocked = true;

    private int badLoginAttempt = 0;

    public void setLastFellLoginAttempt(Instant lastFellLoginAttempt) {
        this.lastFellLoginAttempt = lastFellLoginAttempt;
    }

    public Instant getLastFellLoginAttempt() {
        return lastFellLoginAttempt;
    }

    private Instant lastFellLoginAttempt;

    public int getBadLoginAttempt() {
        return badLoginAttempt;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, String email, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
    }

    protected User() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void failLoginAttempt(Integer maxNumberOfAttempt){
        badLoginAttempt ++;
        lastFellLoginAttempt = Instant.now();
        if (badLoginAttempt >= maxNumberOfAttempt){
            accountNonLocked = false;
        }
    }

    public void checkLogOutExpiration(Duration expirationTime) {
        if(lastFellLoginAttempt != null && lastFellLoginAttempt.plus(expirationTime).isBefore(Instant.now())){
            accountNonLocked = true;
            badLoginAttempt = 0;
            lastFellLoginAttempt = null;
        }
    }
}
