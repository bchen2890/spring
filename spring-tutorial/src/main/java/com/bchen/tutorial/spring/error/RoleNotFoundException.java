package com.bchen.tutorial.spring.error;

public class RoleNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RoleNotFoundException(String id){
        super("The role with id="+id+" doesn't exist.");
    }
}
