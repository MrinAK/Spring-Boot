package eu.ITDC.internetprovider.persistence.repository;

import eu.ITDC.internetprovider.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
