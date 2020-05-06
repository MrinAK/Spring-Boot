package eu.ITDC.internetprovider.persistence.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
//@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    //    @NotBlank
//    @Size(min = 5, max = 20)
    private String username;


    //    @NotBlank
//    @Size(min = 5, max = 80)
    private String password;

    //    @NotBlank
//    @Size(max = 50)
    @Email
    private String email;

    private boolean enabled = true;

    private boolean credentialsNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean accountNonExpired = true;


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
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
