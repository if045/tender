package com.softserveinc.tender.entity;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "company")
public class Company {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "srn")
    private int srn;

    @Column(name = "email")
    private String email;

    @Column(name = "logo")
    private byte[] logo;

    @OneToOne(optional = false)
    @JoinColumn(name="address_id", unique = true, nullable = false, updatable = false)
    private Address address;

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

    public void setBook(Address address) {
        this.address = address;
    }
}
