package eu.itdifferentcources.internetprovider.persistence.repository;

import eu.itdifferentcources.internetprovider.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
