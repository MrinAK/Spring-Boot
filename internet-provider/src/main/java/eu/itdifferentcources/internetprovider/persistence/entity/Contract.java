package eu.itdifferentcources.internetprovider.persistence.entity;

import eu.itdifferentcources.internetprovider.persistence.repository.ContractRepository;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "contracts")
public class Contract extends BaseEntity {

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    private Instant createdAt;

    private Integer length;

    protected Contract() {
    }

    public Contract(Customer customer, Product product, Instant createdAt, Integer length ){
        this.customer = customer;
        this.product = product;
        this.createdAt = createdAt;
        this.length = length;
    }

    public Contract(Customer customer, Product product, Instant now, ContractRepository month) {

    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
