package eu.ITDC.internetprovider.persistence.repository;

import eu.ITDC.internetprovider.persistence.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {
}
