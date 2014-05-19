package com.softserveinc.tender.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy =GenerationType.AUTO )
    @Column
    private Integer id;

    @Column(name = "password", nullable = false, length = 15)
    private String password;


    @Column(name = "create_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date createDate;

    @Column(name = "login", nullable = false, unique = true, length = 30)
    private String login;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    @ManyToMany
    @JoinTable(name = "seller_location", joinColumns = {@JoinColumn(name = "seller_id")}, inverseJoinColumns = {@JoinColumn(name = "location_id")})
    private List<Location> sellerLocations;

    @ManyToMany
    @JoinTable(name = "seller_category", joinColumns = {@JoinColumn(name = "seller_id")}, inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private List<Category> sellerCategories;

    @ManyToMany
    @JoinTable(name = "moderator_category", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "categoty_id")})
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

