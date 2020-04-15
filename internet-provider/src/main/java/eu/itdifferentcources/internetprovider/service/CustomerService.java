package eu.itdifferentcources.internetprovider.service;

import eu.itdifferentcources.internetprovider.persistance.entity.Customer;
import eu.itdifferentcources.internetprovider.persistance.repository.CustomerRepository;
import eu.itdifferentcources.internetprovider.service.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(customer -> new CustomerDTO(
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getCity(),
                        customer.getStreet()))
                .collect(Collectors.toList());
    }

    public void create(CustomerDTO customerDTO) {

        Customer customer = Customer.create(
                customerDTO.getFirstName(),
                customerDTO.getLastName(),
                customerDTO.getCity(),
                customerDTO.getStreet());
        customerRepository.save(customer);
    }


}
