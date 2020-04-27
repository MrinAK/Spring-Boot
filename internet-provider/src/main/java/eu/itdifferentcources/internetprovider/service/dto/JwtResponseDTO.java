package eu.itdifferentcources.internetprovider.service.dto;


import javax.validation.Valid;

@Valid
public class JwtResponseDTO {

    private String token;

    public JwtResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
