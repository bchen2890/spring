package com.bchen.tutorial.spring.model;

import javax.validation.constraints.NotEmpty;

public class User {
    @NotEmpty
    private String username;
    private String name;
    private Integer age;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
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
}
