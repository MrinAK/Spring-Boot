package eu.itdc.internetprovider.service;

import eu.itdc.internetprovider.persistence.entity.Role;
import eu.itdc.internetprovider.persistence.entity.User;
import eu.itdc.internetprovider.persistence.repository.RoleRepository;
import eu.itdc.internetprovider.persistence.repository.UserRepository;
import eu.itdc.internetprovider.service.dto.UserDTO;
import eu.itdc.internetprovider.service.dto.UserRole;
import eu.itdc.internetprovider.service.dto.UserUpdateRolesDTO;
import eu.itdc.internetprovider.service.exception.ResourceNotFound;
import eu.itdc.internetprovider.service.exception.UserNameNotFound;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UserNameNotFound(String.format("User with username %s not found", username)));
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(user.getUsername(), user.getEmail(), databaseRolesToDTORoles(user.getRoles())))
                .collect(Collectors.toList());
    }

    private List<UserRole> databaseRolesToDTORoles(Set<Role> roles) {
        return roles.stream()
                .map(role -> UserRole.valueOf(role.getName().name()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateById(Long userId, UserUpdateRolesDTO userUpdateRolesDTO) {
        User existing = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound(String.format("User with Id %d doesn't exist", userId)));
        Set<Role> updatedRoles = userUpdateRolesDTO.getRoles().stream()
                .map(roleTypeEnum -> roleRepository.findByName(Role.RoleType.valueOf(roleTypeEnum.name()))
                        .orElseThrow(() -> new ResourceNotFound(String.format("User with Id %s doesn't exist", roleTypeEnum)))).collect(Collectors.toSet());
        existing.setRoles(updatedRoles);
        userRepository.save(existing);
        new UserDTO(existing.getUsername(), existing.getEmail(), databaseRolesToDTORoles(existing.getRoles()));
    }
}
