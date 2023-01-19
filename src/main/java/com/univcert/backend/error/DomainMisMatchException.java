package com.univcert.backend.error;

public class DomainMisMatchException extends RuntimeException{
    public DomainMisMatchException() {}
    public DomainMisMatchException(String message) {super(message);}
}
