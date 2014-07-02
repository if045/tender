package com.softserveinc.tender.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

    private static final int COMPANY_NAME_LENGTH = 50;
    private static final int COMPANY_EMAIL_LENGTH = 30;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = COMPANY_NAME_LENGTH)
    private String name;

    @Column(name = "srn")
    private int srn;

    @Column(name = "email", nullable = false, length = COMPANY_EMAIL_LENGTH)
    private String email;

    @Column(name = "logo")
    private byte[] logo;

    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;

    @OneToOne(mappedBy = "company")
    private Profile profile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSrn() {
        return srn;
    }

    public void setSrn(int srn) {
        this.srn = srn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
