package eu.restfulwebservice.service;

import eu.restfulwebservice.persistence.entity.Role;
import eu.restfulwebservice.persistence.entity.User;
import eu.restfulwebservice.persistence.repository.RoleRepository;
import eu.restfulwebservice.persistence.repository.UserRepository;
import eu.restfulwebservice.service.dto.UserDTO;
import eu.restfulwebservice.service.exeption.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void create(UserDTO userDTO) {

        Role role = roleRepository.findById(userDTO.getRoleId()).orElseThrow(ResourceNotFound::new);

        User user = User.create(userDTO.getUserId(),
                userDTO.getMail(),
                role,
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getPassword());
        userRepository.save(user);
    }

    public List<UserDTO> findAll() {

        return userRepository.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getUserId(),
                        user.getMail(),
                        user.getRole().getRoleId(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getPassword()))
                .collect(Collectors.toList());
    }

    public UserDTO findById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(ResourceNotFound::new);

        return new UserDTO(user.getUserId(),
                user.getMail(),
                user.getRole().getRoleId(),
                user.getFirstName(),
                user.getLastName(),
                user.getPassword());
    }

    @Transactional
    public void deleteById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFound::new);
        userRepository.deleteById(userId);
    }

    @Transactional
    public UserDTO updateById(Long userId, UserDTO userDTO) {

        User existing = userRepository.findById(userId)
                .orElseThrow(ResourceNotFound::new);

        Role role = roleRepository.findById(userDTO.getRoleId()).orElseThrow(ResourceNotFound::new);

        User newUser = User.create(userDTO.getUserId(),
                userDTO.getMail(),
                role,
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getPassword());
        existing.update(newUser);
        userRepository.save(existing);

        return new UserDTO(existing.getMail(),
                existing.getFirstName(),
                existing.getLastName(),
                existing.getPassword());
    }
}