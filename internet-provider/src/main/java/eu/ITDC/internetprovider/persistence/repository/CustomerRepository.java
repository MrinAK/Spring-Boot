package eu.ITDC.internetprovider.persistence.repository;

import eu.ITDC.internetprovider.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
