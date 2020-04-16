package eu.itdifferentcources.internetprovider.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private BigDecimal fee;
    private Integer bandwidth;

    private Product(String name, BigDecimal fee, Integer bandwidth) {
        this.name = name;
        this.fee = fee;
        this.bandwidth = bandwidth;
    }

    protected Product() {

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

    public static Product create(String name, BigDecimal fee, Integer bandwidth) {
        return new Product(name, fee, bandwidth);
    }


}
