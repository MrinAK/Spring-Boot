package eu.itdc.internetprovider.service.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Valid
public class ProductDTO {

    private Long id;

    private String status;

    @NotBlank
    @Size(min = 4, max = 100)
    private String name;

    @Digits(integer = 50, fraction = 2)
    @DecimalMin(value = "0", inclusive=false)
    private BigDecimal fee;

    @NotNull
    @Min(0)
    @Max(2000)
    private Integer bandwidth;

    public ProductDTO(Long id, String name, BigDecimal fee, Integer bandwidth, String status) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.bandwidth = bandwidth;
        this.status = status;
    }
    public ProductDTO(Long id, String name, BigDecimal fee, Integer bandwidth) {
        this.id = id;
        this.name = name;
        this.fee = fee;
        this.bandwidth = bandwidth;
    }


    public ProductDTO() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
