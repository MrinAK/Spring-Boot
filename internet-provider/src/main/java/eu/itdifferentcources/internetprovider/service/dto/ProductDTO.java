package eu.itdifferentcources.internetprovider.service.dto;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal fee;
    private Integer bandwidth;

    public ProductDTO(Long id, String name, BigDecimal fee, Integer bandwidth) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.bandwidth = bandwidth;
    }

    public ProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }
}
