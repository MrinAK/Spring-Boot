package eu.ITDC.internetprovider.controller;

import eu.ITDC.internetprovider.service.CustomerService;
import eu.ITDC.internetprovider.service.dto.CustomerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public List<CustomerDTO> getAll() {
        return customerService.findAll();
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Void> create(@RequestBody CustomerDTO customerDTO) {
        customerService.create(customerDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    @PreAuthorize("hasAnyRole('ADMIN' , 'MODERATOR')")
    public CustomerDTO findById(@PathVariable("customerId") Long customerId) {
        return customerService.findById(customerId);
    }

}