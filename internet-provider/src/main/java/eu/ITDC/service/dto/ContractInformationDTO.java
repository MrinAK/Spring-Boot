package eu.internetprovider.service.dto;

import javax.validation.Valid;

@Valid
public class ContractInformationDTO {

    private Long id;

    private CustomerDTO customerDTO;

    private ProductDTO productDTO;

    private Integer month;

    public ContractInformationDTO(Long id, CustomerDTO customerDTO, ProductDTO productDTO, Integer month) {
        this.id = id;
        this.customerDTO = customerDTO;
        this.productDTO = productDTO;
        this.month = month;
    }

    public ContractInformationDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDTO getCustomerDTO() {
        return customerDTO;
    }

    public void setCustomerDTO(CustomerDTO customerDTO) {
        this.customerDTO = customerDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
