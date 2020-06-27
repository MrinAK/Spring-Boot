package eu.itdc.internetprovider.service.dto;

import javax.validation.Valid;

@Valid
public class CustomerDTO {

    private ClientTypeEnum clientType;

    private Long id;

    private String firstName;

    private String lastName;

    private String city;

    private String street;

    private String companyName;

    private String vatNumber;

    private String responsiblePerson;

    public CustomerDTO(Long id, String city, String street, String companyName, String vatNumber, String responsiblePerson) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.companyName = companyName;
        this.vatNumber = vatNumber;
        this.responsiblePerson = responsiblePerson;
    }

    public CustomerDTO(Long id, String firstName, String lastName, String city, String street) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
    }

    public CustomerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientTypeEnum getClientType() {
        return clientType;
    }

    public void setClientType(ClientTypeEnum clientType) {
        this.clientType = clientType;
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
}
