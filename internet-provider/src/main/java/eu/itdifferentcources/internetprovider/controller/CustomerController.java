package eu.itdifferentcources.internetprovider.controller;

import eu.itdifferentcources.internetprovider.service.CustomerService;
import eu.itdifferentcources.internetprovider.service.dto.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<CustomerDTO> getAll() {
        return customerService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Void> Create(@RequestBody CustomerDTO customerDTO) {
        customerService.create(customerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}