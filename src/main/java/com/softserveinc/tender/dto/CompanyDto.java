package com.softserveinc.tender.dto;

public class CompanyDto {

    private String name;
    private String srnNumber;
    private String email;
    private AddressDto addressDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSrnNumber() {
        return srnNumber;
    }

    public void setSrnNumber(String srnNumber) {
        this.srnNumber = srnNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }
}
