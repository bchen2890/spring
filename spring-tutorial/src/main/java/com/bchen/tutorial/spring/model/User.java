package com.bchen.tutorial.spring.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class User {
    private Date lastLogin;

    @NotEmpty
    @Size(min=3, max=10, message = "The username must be between 3 and 10 characters")
    @Pattern(regexp = "[a-z0-9]*")
    private String username;

    @NotBlank
    private String name;

    @Max(120)
    @Min(18)
    private Integer age;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @PasswordRegex
    private String password;

    @Past //Also @Future
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull
    private Country country;

    @NotEmpty
    private List<Role> roles;

    public User(){

    }

    public User(String name, Integer age){
        this.age = age;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
