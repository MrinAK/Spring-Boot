package eu.itdifferentcources.internetprovider.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 *
 */
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;

    private BigDecimal fee;

    private Integer bandwidth;

    @OneToOne
    private User createdBy;

    private Product(String name, BigDecimal fee, Integer bandwidth, User createdBy) {
        this.name = name;
        this.fee = fee;
        this.bandwidth = bandwidth;
        this.createdBy = createdBy;
    }

    protected Product() {
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
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

    public static Product create(String name, BigDecimal fee, Integer bandwidth,User createdBy) {
        return new Product(name, fee, bandwidth, createdBy);
    }
}
