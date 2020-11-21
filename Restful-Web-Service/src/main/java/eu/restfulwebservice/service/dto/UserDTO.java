package eu.restfulwebservice.service.dto;

import eu.restfulwebservice.persistence.entity.Role;

public class UserDTO {

    private Long userId;

    private String mail;

    private Long roleId;

    private String firstName;

    private String lastName;

    private String password;

    public UserDTO(Long userId, String mail,Long roleId, String firstName, String lastName, String password) {
        this.userId = userId;
        this.mail = mail;
        this.roleId = roleId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UserDTO(String mail, String firstName, String lastName, String password) {
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public UserDTO() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
