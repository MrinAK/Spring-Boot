package eu.itdifferentcources.internetprovider.persistance.repository;

import eu.itdifferentcources.internetprovider.persistance.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
