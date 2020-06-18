package eu.itdc.internetprovider.service;

import eu.itdc.internetprovider.persistence.entity.Product;
import eu.itdc.internetprovider.persistence.repository.ProductRepository;
import eu.itdc.internetprovider.service.dto.ProductDTO;
import eu.itdc.internetprovider.service.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public ProductService(ProductRepository productRepository, AuthenticationFacade authenticationFacade) {
        this.productRepository = productRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getFee(),
                        product.getBandwidth(),
                        product.getStatus().name()))
                .collect(Collectors.toList());
    }

    public void create(ProductDTO productDTO) {
        Product product = Product.create(productDTO.getName(),
                productDTO.getFee(),
                productDTO.getBandwidth(),
                authenticationFacade.getAuthentication());
        productRepository.save(product);
    }

    public ProductDTO findById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFound(String.format("Product with Id %d doesn't exist", productId)));
        return new ProductDTO(product.getId(),
                product.getName(),
                product.getFee(),
                product.getBandwidth(),
                product.getStatus().name());
    }

    @Transactional
    public void deleteById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFound(String.format("Product with Id %d doesn't exist", productId)));
        product.delete();
        productRepository.save(product);

    }

    @Transactional
    public ProductDTO updateById(Long productId, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFound(String.format("Product with Id %d doesn't exist", productId)));

        Product updatedProduct = Product.create(productDTO.getName(),
                productDTO.getFee(),
                productDTO.getBandwidth(),
                authenticationFacade.getAuthentication());

        existingProduct.update(updatedProduct);
        productRepository.save(existingProduct);

        return new ProductDTO(existingProduct.getId(),
                existingProduct.getName(),
                existingProduct.getFee(),
                existingProduct.getBandwidth(),
                existingProduct.getStatus().name());
    }
}
