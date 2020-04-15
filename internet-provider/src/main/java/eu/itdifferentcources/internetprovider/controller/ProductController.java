package eu.itdifferentcources.internetprovider.controller;

import eu.itdifferentcources.internetprovider.service.ProductService;
import eu.itdifferentcources.internetprovider.service.dto.ProductDTO;
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
    public void Create(@RequestBody ProductDTO productDTO) {
        productService.create(productDTO);
    }
}
