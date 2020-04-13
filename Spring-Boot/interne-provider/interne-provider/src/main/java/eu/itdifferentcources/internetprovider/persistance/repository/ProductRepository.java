package eu.itdifferentcources.internetprovider.persistance.repository;

import eu.itdifferentcources.internetprovider.persistance.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
