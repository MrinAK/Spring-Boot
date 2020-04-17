package eu.itdifferentcources.internetprovider.service;

import eu.itdifferentcources.internetprovider.persistence.entity.Product;
import eu.itdifferentcources.internetprovider.persistence.repository.ProductRepository;
import eu.itdifferentcources.internetprovider.service.dto.ProductDTO;
import eu.itdifferentcources.internetprovider.service.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getFee(),
                        product.getBandwidth()))
                .collect(Collectors.toList());
    }

    public void create(ProductDTO productDTO) {

        Product product = Product.create(productDTO.getName(), productDTO.getFee(), productDTO.getBandwidth());
        productRepository.save(product);
    }

    public ProductDTO findById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->new ResourceNotFound(String.format("Product with Id %d doesn't exist", productId)));
        return new ProductDTO(product.getId(),
                product.getName(),
                product.getFee(),
                product.getBandwidth());
    }
}
