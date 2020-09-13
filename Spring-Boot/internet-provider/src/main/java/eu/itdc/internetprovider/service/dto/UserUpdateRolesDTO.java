package eu.itdc.internetprovider.service.dto;

import javax.validation.Valid;
import java.util.List;

@Valid
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
