package eu.itdifferentcources.internetprovider.persistence.repository;

import eu.itdifferentcources.internetprovider.persistence.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {
}
