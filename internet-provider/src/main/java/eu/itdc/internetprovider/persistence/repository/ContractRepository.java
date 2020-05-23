package eu.itdc.internetprovider.persistence.repository;

import eu.itdc.internetprovider.persistence.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
}
