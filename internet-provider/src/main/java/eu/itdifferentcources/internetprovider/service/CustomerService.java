package eu.itdifferentcources.internetprovider.service;

import eu.itdifferentcources.internetprovider.persistence.entity.Customer;
import eu.itdifferentcources.internetprovider.persistence.repository.CustomerRepository;
import eu.itdifferentcources.internetprovider.service.dto.CustomerDTO;
import eu.itdifferentcources.internetprovider.service.exception.ResourceNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, AuthenticationFacade authenticationFacade) {
        this.customerRepository = customerRepository;
        this.authenticationFacade = authenticationFacade;
    }

    /**
     *
     * @return {@link List<CustomerDTO>}
     */
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
                customerDTO.getStreet(),
                authenticationFacade.getAuthentication());
        customerRepository.save(customer);
    }


    public CustomerDTO findById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFound(String.format("Customer with Id %d doesn't exist", customerId)));
        return new CustomerDTO(customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getCity(),
                customer.getStreet()
        );
    }
}
