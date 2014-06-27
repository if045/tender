package com.softserveinc.tender.dto;

import java.util.List;

public class UsersProfileDataDto {

    private PersonalInfoDto personalInfoDto;
    private CompanyDto companyDto;
    private TradeSphereDto tradeSphereDto;
    private List<RatingDto> ratingDto;

    public PersonalInfoDto getPersonalInfoDto() {
        return personalInfoDto;
    }

    public void setPersonalInfoDto(PersonalInfoDto personalInfoDto) {
        this.personalInfoDto = personalInfoDto;
    }

    public CompanyDto getCompanyDto() {
        return companyDto;
    }

    public void setCompanyDto(CompanyDto companyDto) {
        this.companyDto = companyDto;
    }

    public TradeSphereDto getTradeSphereDto() {
        return tradeSphereDto;
    }

    public void setTradeSphereDto(TradeSphereDto tradeSphereDto) {
        this.tradeSphereDto = tradeSphereDto;
    }

    public List<RatingDto> getRatingDto() {
        return ratingDto;
    }

    public void setRatingDto(List<RatingDto> ratingDto) {
        this.ratingDto = ratingDto;
    }
}
