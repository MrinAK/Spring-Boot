package eu.internetprovider.controller;

import eu.internetprovider.service.AuthenticationService;
import eu.internetprovider.service.dto.JwtResponseDTO;
import eu.internetprovider.service.dto.LoginRequestDTO;
import eu.internetprovider.service.dto.SignupRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/v1/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody @Validated SignupRequestDTO createUserDTO){
        authenticationService.signup(createUserDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public JwtResponseDTO signin(@RequestBody @Validated LoginRequestDTO loginRequestDTO){
        return authenticationService.signin(loginRequestDTO);
    }
}
