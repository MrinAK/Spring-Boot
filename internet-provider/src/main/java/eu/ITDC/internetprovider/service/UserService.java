package eu.ITDC.internetprovider.service;

import eu.ITDC.internetprovider.persistence.repository.UserRepository;
import eu.ITDC.internetprovider.service.exception.UserNameNotFound;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor

@Primary
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
