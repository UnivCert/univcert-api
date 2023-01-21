package com.univcert.backend.error;

public class ApiNotFoundException extends RuntimeException{
    public ApiNotFoundException() {}
    public ApiNotFoundException(String message) {super(message);}
}
