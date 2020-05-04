package eu.itdifferentcources.internetprovider.controller;

import eu.itdifferentcources.internetprovider.service.ProductService;
import eu.itdifferentcources.internetprovider.service.dto.ProductDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/products")
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<ProductDTO> getAll() {
        return productService.findAll();
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ResponseEntity<Void> create(@RequestBody @Validated ProductDTO productDTO) {
        productService.create(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ProductDTO findById(@PathVariable("productId") Long productId) {
        return productService.findById(productId);
    }
}
