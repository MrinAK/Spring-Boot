package eu.itdifferentcources.internetprovider.service;

import eu.itdifferentcources.internetprovider.persistence.repository.UserRepository;
import eu.itdifferentcources.internetprovider.service.exception.UserNameNotFound;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UserNameNotFound(String.format("User with username %s not found", username)));
    }
}
