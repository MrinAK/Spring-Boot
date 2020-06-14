package eu.itdc.internetprovider.service.dto;

import javax.validation.Valid;
import java.util.List;

@Valid
public class UserDTO {

    private String username;

    private String email;

    private List<UserRole> roles;

    public UserDTO(String username, String email, List<UserRole> roles) {
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
