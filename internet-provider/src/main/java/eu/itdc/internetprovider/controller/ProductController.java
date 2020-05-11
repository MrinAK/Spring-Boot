package eu.itdc.internetprovider.controller;

import eu.itdc.internetprovider.service.ProductService;
import eu.itdc.internetprovider.service.dto.ProductDTO;
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
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<Void> create(@RequestBody @Validated ProductDTO productDTO) {
        productService.create(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ProductDTO findById(@PathVariable("productId") Long productId) {
        return productService.findById(productId);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<Void> delete(@PathVariable("productId") Long productId){
        productService.deleteById(productId);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ProductDTO updateById(@PathVariable("productId") Long productId,@RequestBody @Validated ProductDTO productDTO) {
        return productService.updateById(productId, productDTO);
    }
}
