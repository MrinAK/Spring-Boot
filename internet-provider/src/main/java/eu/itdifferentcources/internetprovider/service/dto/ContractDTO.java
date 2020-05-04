package eu.itdifferentcources.internetprovider.service.dto;

import javax.validation.Valid;

@Valid
public class ContractDTO {

    private Long customerId;

    private Long productId;

    private Integer month;

    public ContractDTO(Long customerId, Long productId, Integer month) {
        this.customerId = customerId;
        this.productId = productId;
        this.month = month;
    }

    public ContractDTO() {
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
