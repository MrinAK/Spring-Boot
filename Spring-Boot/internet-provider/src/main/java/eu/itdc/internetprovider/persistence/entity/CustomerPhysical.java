package eu.itdc.internetprovider.persistence.entity;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerPhysical {

    private String firstName;

    private String lastName;

    public CustomerPhysical(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public CustomerPhysical() {
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
}
