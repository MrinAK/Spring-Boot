package eu.restfulwebservice.service;

import eu.restfulwebservice.persistence.entity.Role;
import eu.restfulwebservice.persistence.repository.RoleRepository;
import eu.restfulwebservice.service.dto.RoleDTO;
import eu.restfulwebservice.service.exeption.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void create(RoleDTO roleDTO) {
        Role role = Role.create(roleDTO.getRoleId(),
                roleDTO.getRoleName());
        roleRepository.save(role);
    }

    public List<RoleDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(role -> new RoleDTO(
                        role.getRoleId(),
                        role.getRoleName()))
                .collect(Collectors.toList());
    }

    public RoleDTO findById(Long roleId) {

        Role role = roleRepository.findById(roleId).orElseThrow(ResourceNotFound::new);

        return new RoleDTO(role.getRoleId(), role.getRoleName());
    }

    @Transactional
    public void deleteById(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(ResourceNotFound::new);
        roleRepository.deleteById(roleId);
    }

    @Transactional
    public Object updateById(Long roleId, RoleDTO roleDTO) {
        Role existing = roleRepository.findById(roleId).orElseThrow(ResourceNotFound::new);

        Role newRole = Role.create(roleDTO.getRoleId(),
                roleDTO.getRoleName());
        existing.update(newRole);
        roleRepository.save(existing);

        return new RoleDTO(existing.getRoleName());
    }
}
