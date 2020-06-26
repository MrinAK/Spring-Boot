package eu.itdc.internetprovider.service;

import eu.itdc.internetprovider.persistence.entity.Role;
import eu.itdc.internetprovider.persistence.entity.User;
import eu.itdc.internetprovider.persistence.repository.RoleRepository;
import eu.itdc.internetprovider.persistence.repository.UserRepository;
import eu.itdc.internetprovider.service.dto.JwtResponseDTO;
import eu.itdc.internetprovider.service.dto.LoginRequestDTO;
import eu.itdc.internetprovider.service.dto.SignupRequestDTO;
import eu.itdc.internetprovider.service.util.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Value("${app.login.maxNumberOfAttempt}")
    private Integer maxNumberOfAttempt;

    public AuthenticationService(UserRepository userRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    private static final Map<Role.RoleType, Role> roles = new HashMap<>();

    @PostConstruct
    protected void postConstruct() {
        roleRepository.findAll().forEach(role -> roles.put(role.getName(), role));
    }

    public void signup(SignupRequestDTO createUserDTO) {

        Role role = roles.get(Role.RoleType.ROLE_CUSTOMER);

        if (userRepository.count() == 0) {
            role = roles.get(Role.RoleType.ROLE_ADMIN);
        }

        if (userRepository.findByUsername(createUserDTO.getUsername()).isPresent()) {
            throw new RuntimeException(String.format("Username %s already exist", createUserDTO.getUsername()));
        }

        User user = new User(createUserDTO.getUsername(), passwordEncoder.encode(createUserDTO.getPassword()),
                createUserDTO.getEmail(),
                Set.of(role));
        userRepository.save(user);
    }


    public JwtResponseDTO signin(LoginRequestDTO loginRequestDTO) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()));

        } catch (BadCredentialsException exception) {
            userRepository.findByUsername(loginRequestDTO.getUsername()).ifPresent(user -> {
                user.failLoginAttempt(maxNumberOfAttempt);
                userRepository.save(user);
            });
            throw exception;
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generatedJwtToken(authentication);
        return new JwtResponseDTO(jwt);
    }
}