package eu.itdifferentcources.internetprovider.service.dto;

public class DetailContractDTO {

    private Long id;

    private CustomerDTO customerDTO;

    private ProductDTO productDTO;

    public DetailContractDTO(Long id, CustomerDTO customerDTO, ProductDTO productDTO) {
        this.id = id;
        this.customerDTO = customerDTO;
        this.productDTO = productDTO;
    }

    public DetailContractDTO() {
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
}
