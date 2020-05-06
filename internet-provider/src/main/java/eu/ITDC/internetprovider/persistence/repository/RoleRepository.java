package eu.ITDC.internetprovider.persistence.repository;

import eu.ITDC.internetprovider.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(Role.RoleType name);
}
