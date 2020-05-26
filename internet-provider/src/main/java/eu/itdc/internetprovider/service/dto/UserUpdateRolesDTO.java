package eu.itdc.internetprovider.service.dto;

import eu.itdc.internetprovider.persistence.entity.Role;

import java.util.List;

public class UserUpdateRolesDTO {

    private List<RoleTypeEnum> roles;

    public UserUpdateRolesDTO() {
    }

    public List<RoleTypeEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleTypeEnum> roles) {
        this.roles = roles;
    }
}
