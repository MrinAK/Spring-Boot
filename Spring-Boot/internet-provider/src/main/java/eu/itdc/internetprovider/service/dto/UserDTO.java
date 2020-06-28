package eu.itdc.internetprovider.service.dto;

import javax.validation.Valid;
import java.util.List;

@Valid
public class UserDTO {

    private String username;

    private String email;

    private List<UserRoleEnum> roles;

    public UserDTO(String username, String email, List<UserRoleEnum> roles) {
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

    public List<UserRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEnum> roles) {
        this.roles = roles;
    }
}
