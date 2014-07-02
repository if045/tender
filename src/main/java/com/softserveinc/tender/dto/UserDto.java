package com.softserveinc.tender.dto;

import com.softserveinc.tender.entity.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class UserDto {

    private  Integer id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    @NotNull
    private List<Integer> roles;

    private List<RoleDto> rolesNames;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<RoleDto> getRolesNames() {
        return rolesNames;
    }

    public void setRolesNames(List<RoleDto> rolesNames) {
        this.rolesNames = rolesNames;
    }
}
