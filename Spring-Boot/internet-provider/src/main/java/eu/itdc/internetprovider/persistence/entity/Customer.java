package eu.itdc.internetprovider.persistence.entity;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @Embedded
    private CustomerPhysical customerPhysical;

    @Embedded
    private CustomerLegal customerLegal;

    private String city;

    private String street;

    @OneToOne
    private User user;

    private Customer(CustomerPhysical customerPhysical, CustomerLegal customerLegal, ClientType clientType, String city, String street, User user) {
        this.customerPhysical = customerPhysical;
        this.customerLegal = customerLegal;
        this.clientType = clientType;
        this.city = city;
        this.street = street;
        this.user = user;
    }

    protected Customer() {
    }

    public User getUser() {
        return user;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public CustomerPhysical getCustomerPhysical() {
        return customerPhysical;
    }

    public void setCustomerPhysical(CustomerPhysical customerPhysical) {
        this.customerPhysical = customerPhysical;
    }

    public CustomerLegal getCustomerLegal() {
        return customerLegal;
    }

    public void setCustomerLegal(CustomerLegal customerLegal) {
        this.customerLegal = customerLegal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public static Customer createPhysical(String firstName, String lastName, String city, String street, User user) {
        return new Customer(new CustomerPhysical(firstName, lastName), null, ClientType.PHYSICAL, city, street, user);
    }

    public static Customer createLegal(String companyName, String vatNumber,String responsiblePerson, String city, String street, User user) {
        return new Customer(null,new CustomerLegal(companyName, vatNumber, responsiblePerson), ClientType.LEGAL, city, street, user);
    }
}
