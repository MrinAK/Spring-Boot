package eu.itdifferentcources.internetprovider.service.dto;

import javax.validation.Valid;

@Valid
public class LoginRequestDTO {

private String username;

private String password;

    public LoginRequestDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginRequestDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
