package eu.ITDC.internetprovider.service;

import eu.ITDC.internetprovider.persistence.entity.Role;
import eu.ITDC.internetprovider.persistence.entity.User;
import eu.ITDC.internetprovider.persistence.repository.RoleRepository;
import eu.ITDC.internetprovider.persistence.repository.UserRepository;
import eu.ITDC.internetprovider.service.dto.JwtResponseDTO;
import eu.ITDC.internetprovider.service.dto.LoginRequestDTO;
import eu.ITDC.internetprovider.service.dto.SignupRequestDTO;
import eu.ITDC.internetprovider.service.util.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
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

    //Todo
    @PostConstruct
    protected void postConstruct() {
        roleRepository.findAll().stream().forEach(role -> roles.put(role.getName(), role));
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

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(),
                        loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generatedJwtToken(authentication);
        return new JwtResponseDTO(jwt);
    }
}