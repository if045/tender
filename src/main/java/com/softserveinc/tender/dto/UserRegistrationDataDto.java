package com.softserveinc.tender.dto;

import javax.validation.constraints.NotNull;

public class UserRegistrationDataDto {

    private UserDto userDto;
    private ProfileDto profileDto;
    private CompanyDto companyDto;
    private TradeSphereDto tradeSphereDto;

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public ProfileDto getProfileDto() {
        return profileDto;
    }

    public void setProfileDto(ProfileDto profileDto) {
        this.profileDto = profileDto;
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
}
