package eu.restfulwebservice.service.dto;

public class RoleDTO {

    private Long roleId;

    private String roleName;

    public RoleDTO(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public RoleDTO(String roleName) {
        this.roleName = roleName;
    }

    public RoleDTO() {
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}
