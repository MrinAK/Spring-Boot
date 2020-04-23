package eu.itdifferentcources.internetprovider.service;

import eu.itdifferentcources.internetprovider.persistence.entity.Contract;
import eu.itdifferentcources.internetprovider.persistence.entity.Customer;
import eu.itdifferentcources.internetprovider.persistence.entity.Product;
import eu.itdifferentcources.internetprovider.persistence.repository.ContractRepository;
import eu.itdifferentcources.internetprovider.persistence.repository.CustomerRepository;
import eu.itdifferentcources.internetprovider.persistence.repository.ProductRepository;
import eu.itdifferentcources.internetprovider.service.dto.ContractDTO;
import eu.itdifferentcources.internetprovider.service.dto.ContractInformationDTO;
import eu.itdifferentcources.internetprovider.service.dto.CustomerDTO;
import eu.itdifferentcources.internetprovider.service.dto.ProductDTO;
import eu.itdifferentcources.internetprovider.service.exception.ResourceNotFound;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractService {

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private final ContractRepository contractRepository;

    public ContractService(CustomerRepository customerRepository, ProductRepository productRepository, ContractRepository contractRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.contractRepository = contractRepository;
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
                            product.getBandwidth());

                    return new ContractInformationDTO(contract.getId(),customerDTO,productDTO,contract.getLength());
                })
                .collect(Collectors.toList());
    }


    public void create(ContractDTO contractDTO) {
        Product product = productRepository.findById(contractDTO.getProductId())
                .orElseThrow(() -> new ResourceNotFound(String.format("Product with Id %d doesn't exist", contractDTO.getProductId())));

        Customer customer = customerRepository.findById(contractDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFound(String.format("Customer with Id %d doesn't exist", contractDTO.getCustomerId())));
        contractRepository.save(new Contract(customer, product, Instant.now(), contractDTO.getMonth()));
    }
}
