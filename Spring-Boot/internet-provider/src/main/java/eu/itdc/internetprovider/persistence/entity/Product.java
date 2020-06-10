package eu.itdc.internetprovider.persistence.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Audited
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;

    private BigDecimal fee;

    private Integer bandwidth;

    @Audited(targetAuditMode = NOT_AUDITED)
    @OneToOne
    private User createdBy;

    @CreationTimestamp
    private Timestamp createAt;

    @UpdateTimestamp
    private Timestamp updateAt;

    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVE;

    private Product(String name, BigDecimal fee, Integer bandwidth, User createdBy) {
        this.name = name;
        this.fee = fee;
        this.bandwidth = bandwidth;
        this.createdBy = createdBy;
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

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void update(Product newProduct){
        this.name = newProduct.name;
        this.bandwidth = newProduct.bandwidth;
        this.fee = newProduct.fee;
    }

    public static Product create(String name, BigDecimal fee, Integer bandwidth, User createdBy) {
        return new Product(name, fee, bandwidth, createdBy);
    }

    public void delete() {
        this.status = Status.DELETED;
    }
}
