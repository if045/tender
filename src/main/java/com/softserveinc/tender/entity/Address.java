package com.softserveinc.tender.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    private static final int ADDRESS_CITY_LENGTH = 30;
    private static final int ADDRESS_STREET_LENGTH = 30;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(name = "city", length = ADDRESS_CITY_LENGTH)
    private String city;

    @Column(name = "street", length = ADDRESS_STREET_LENGTH)
    private String street;

    @Column(name = "building_number")
    private int buildingNumber;

    @Column(name = "postcode")
    private int postcode;

    @OneToOne(mappedBy="address")
    public Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }
}
