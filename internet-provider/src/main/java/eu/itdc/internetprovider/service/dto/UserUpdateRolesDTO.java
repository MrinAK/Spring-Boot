package eu.itdc.internetprovider.service.dto;

import java.util.List;

public class UserUpdateRolesDTO {

    private List<String> roles;

    public UserUpdateRolesDTO(List<String> roles) {
        this.roles = roles;
    }

    public UserUpdateRolesDTO() {
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
