package eu.itdifferentcources.internetprovider.persistence.repository;

import eu.itdifferentcources.internetprovider.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
