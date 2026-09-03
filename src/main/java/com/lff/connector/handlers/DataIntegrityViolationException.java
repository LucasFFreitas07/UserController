package com.lff.connector.handlers;

public class DataIntegrityViolationException extends RuntimeException{

    public DataIntegrityViolationException (String message){
        super(message);
    }

}
