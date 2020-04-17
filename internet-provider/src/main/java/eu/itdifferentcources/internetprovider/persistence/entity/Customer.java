package eu.itdifferentcources.internetprovider.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    private String firstName;
    private String lastName;
    private String city;
    private String street;

    private Customer(String firstName, String lastName, String city, String street) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
    }

    protected Customer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public static Customer create(String firstName, String lastName, String city, String street) {
        return new Customer(firstName, lastName, city, street);
    }
}
