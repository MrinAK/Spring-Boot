package eu.itdc.internetprovider.service;

import eu.itdc.internetprovider.persistence.entity.Customer;
import eu.itdc.internetprovider.persistence.repository.CustomerRepository;
import eu.itdc.internetprovider.service.dto.ClientType;
import eu.itdc.internetprovider.service.dto.CustomerDTO;
import eu.itdc.internetprovider.service.exception.ResourceNotFound;
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
     * @return {@link List<CustomerDTO>}
     */
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerService::customerEntityToDTO)
                .collect(Collectors.toList());
    }

    public void create(CustomerDTO customerDTO) {

        if (customerDTO.getClientType() == ClientType.PHYSICAL) {

            Customer customer = Customer.createPhysical(
                    customerDTO.getFirstName(),
                    customerDTO.getLastName(),
                    customerDTO.getCity(),
                    customerDTO.getStreet(),
                    authenticationFacade.getAuthentication());
            customerRepository.save(customer);
        }

        if (customerDTO.getClientType() == ClientType.LEGAL) {

            Customer customer = Customer.createLegal(
                    customerDTO.getCompanyName(),
                    customerDTO.getVatNumber(),
                    customerDTO.getResponsiblePerson(),
                    customerDTO.getCity(),
                    customerDTO.getStreet(),
                    authenticationFacade.getAuthentication());
            customerRepository.save(customer);
        }
    }

    public CustomerDTO findById(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFound(String.format("Customer with Id %d doesn't exist", customerId)));
        return customerEntityToDTO(customer);
    }

    public static CustomerDTO customerEntityToDTO(Customer customer) {
        if (customer.getClientType() == eu.itdc.internetprovider.persistence.entity.ClientType.PHYSICAL) {
            return new CustomerDTO(
                    customer.getId(),
                    customer.getCustomerPhysical().getFirstName(),
                    customer.getCustomerPhysical().getLastName(),
                    customer.getCity(),
                    customer.getStreet());
        } else {
            return new CustomerDTO(
                    customer.getId(),
                    customer.getCustomerLegal().getCompanyName(),
                    customer.getCustomerLegal().getVatNumber(),
                    customer.getCustomerLegal().getResponsiblePerson(),
                    customer.getCity(),
                    customer.getStreet());
        }
    }
}
