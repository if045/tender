package com.softserveinc.tender.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "password")
    private String password;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "login")
    private String login;

    @ManyToMany
    @JoinTable
    private List<Role> roles;

    @ManyToMany
    @JoinTable
    private List<Location> sellerLocations;

    @ManyToMany
    @JoinTable
    private List<Category> sellerCategories;

    @ManyToMany
    @JoinTable
    private List<Category> moderatorCategories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Location> getSellerLocations() {
        return sellerLocations;
    }

    public void setSellerLocations(List<Location> sellerLocations) {
        this.sellerLocations = sellerLocations;
    }

    public List<Category> getSellerCategories() {
        return sellerCategories;
    }

    public void setSellerCategories(List<Category> sellerCategories) {
        this.sellerCategories = sellerCategories;
    }

    public List<Category> getModeratorCategories() {
        return moderatorCategories;
    }

    public void setModeratorCategories(List<Category> moderatorCategories) {
        this.moderatorCategories = moderatorCategories;
    }
}

