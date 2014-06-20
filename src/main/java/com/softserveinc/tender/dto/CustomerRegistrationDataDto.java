package com.softserveinc.tender.dto;

import javax.validation.constraints.NotNull;

public class CustomerRegistrationDataDto {

    @NotNull
    private UserDto userDto;

    @NotNull
    private ProfileDto profileDto;

    private CompanyDto companyDto;

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
}
