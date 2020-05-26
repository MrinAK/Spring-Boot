package eu.itdc.internetprovider.service;

import eu.itdc.internetprovider.persistence.entity.Contract;
import eu.itdc.internetprovider.persistence.entity.Customer;
import eu.itdc.internetprovider.persistence.entity.Product;
import eu.itdc.internetprovider.persistence.entity.Status;
import eu.itdc.internetprovider.persistence.repository.ContractRepository;
import eu.itdc.internetprovider.persistence.repository.CustomerRepository;
import eu.itdc.internetprovider.persistence.repository.ProductRepository;
import eu.itdc.internetprovider.service.dto.ContractDTO;
import eu.itdc.internetprovider.service.dto.ContractInformationDTO;
import eu.itdc.internetprovider.service.dto.CustomerDTO;
import eu.itdc.internetprovider.service.dto.ProductDTO;
import eu.itdc.internetprovider.service.exception.ResourceNotFound;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractService {

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final ContractRepository contractRepository;

    private final AuthenticationFacade authenticationFacade;

    public ContractService(CustomerRepository customerRepository,
                           ProductRepository productRepository,
                           ContractRepository contractRepository,
                           AuthenticationFacade authenticationFacade) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.contractRepository = contractRepository;
        this.authenticationFacade = authenticationFacade;
    }

    public List<ContractInformationDTO> findAll() {
        return contractRepository.findAll()
                .stream()
                .map(contract -> {
                    Customer customer = contract.getCustomer();
                    Product product = contract.getProduct();

                    CustomerDTO customerDTO = new CustomerDTO(
                            customer.getId(),
                            customer.getFirstName(),
                            customer.getLastName(),
                            customer.getCity(),
                            customer.getStreet());

                    ProductDTO productDTO = new ProductDTO(product.getId(),
                            product.getName(),
                            product.getFee(),
                            product.getBandwidth(),
                            product.getStatus().name());

                    return new ContractInformationDTO(contract.getId(), customerDTO, productDTO, contract.getLength());
                })
                .collect(Collectors.toList());
    }


    public void create(ContractDTO contractDTO) {
        Product product = productRepository.findById(contractDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFound(String.format("Product with Id %d doesn't exist", contractDTO.getProductId())));

        if (product.getStatus() == Status.DELETED) {
            throw new ResourceNotFound(String.format("Product with Id %d doesn't exist", contractDTO.getProductId()));
        }

        Customer customer = customerRepository.findById(contractDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFound(String.format("Customer with Id %d doesn't exist", contractDTO.getCustomerId())));
        contractRepository.save(new Contract(customer,
                product, Instant.now(),
                contractDTO.getMonth(),
                authenticationFacade.getAuthentication()));
    }


    public ContractDTO findById(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFound(String.format("Contract with Id %d doesn't exist", contractId)));
        return new ContractDTO(contract.getCustomer().getId(),
                contract.getProduct().getId(),
                contract.getLength());
    }

    @Transactional
    public void deleteById(Long contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new ResourceNotFound(String.format("Contract with Id %d doesn't exist", contractId)));
        contractRepository.delete(contract);
    }
}
