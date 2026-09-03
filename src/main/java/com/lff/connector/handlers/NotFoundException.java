package com.lff.connector.handlers;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }
}
