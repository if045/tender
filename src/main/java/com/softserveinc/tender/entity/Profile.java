package com.softserveinc.tender.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue
    @Column(name = "id",nullable = false,length = 11)
    private Integer id;

    @Column(name = "icon")
    private String icon;

    @Column(name = "first_name",nullable = false,length = 20)
    private String firstName;

    @Column(name = "last_name",nullable = false, length = 20)
    private String lastName;

    @Column(name = "birthday")
    @Temporal(value=TemporalType.DATE)
    private Date birthday;

    @Column(name = "telephone",nullable =false,length = 20)
    private String telephone;

    @Column(name = "person",nullable =false,length = 1)
    private Character person;

    @Column(name = "checked",nullable = false, length = 1)
    private Boolean checked;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Character getPerson() {
        return person;
    }

    public void setPerson(Character person) {
        this.person = person;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
