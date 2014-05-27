package com.softserveinc.tender.dto;

public class ProposalDto {

    public static final int MAX_PERCENTAGE_VALUE = 100;

    private Integer id;
    private String fullName;
    private Double totalPrice;

    private Integer numberOfBids;
    private String firstName;
    private String lastName;
    private Double sum;
    private Double discountPercentage;
    private Double discountCurrency;

    public ProposalDto() {};

    public ProposalDto(Integer id, Integer numberOfBids, String firstName, String lastName, Double sum, Double discountPercentage, Double discountCurrency) {
        this.id = id;
        this.numberOfBids = numberOfBids;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sum = sum;
        this.discountPercentage = discountPercentage;
        this.discountCurrency = discountCurrency;

        fullName = setFullName();
        totalPrice = setTotalPrice();
    }

    private Double setTotalPrice() {
        if(discountPercentage != null) {
            return sum - (sum * (getDiscountPercentage() / MAX_PERCENTAGE_VALUE));
        } else {
            return sum - getDiscountCurrency();
        }
    }

    private String setFullName() {
        return getFirstName() + " " + getLastName();
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getDiscountCurrency() {
        return discountCurrency;
    }

    public void setDiscountCurrency(Double discountCurrency) {
        this.discountCurrency = discountCurrency;
    }
}
