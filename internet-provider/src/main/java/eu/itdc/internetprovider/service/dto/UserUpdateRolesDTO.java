package eu.itdc.internetprovider.service.dto;

import eu.itdc.internetprovider.persistence.entity.Role;

import java.util.List;

public class UserUpdateRolesDTO {
/*
//    private List<String> roles;
//    public UserUpdateRolesDTO(List<String> roles) {
//        this.roles = roles;
//    }
//    public UserUpdateRolesDTO() {
//    }
//    public List<String> getRoles() {
//        return roles;
//    }
//    public void setRoles(List<String> roles) {
//        this.roles = roles;
//    }
*/

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
