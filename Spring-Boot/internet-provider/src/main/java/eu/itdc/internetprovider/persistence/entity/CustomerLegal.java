package eu.itdc.internetprovider.persistence.entity;

import javax.persistence.Embeddable;

@Embeddable
public class CustomerLegal {

    private String companyName;

    private String vatNumber;

    private String responsiblePerson;

    public CustomerLegal(String companyName, String vatNumber, String responsiblePerson) {
        this.companyName = companyName;
        this.vatNumber = vatNumber;
        this.responsiblePerson = responsiblePerson;
    }

    public CustomerLegal() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }
}
