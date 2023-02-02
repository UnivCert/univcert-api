package com.univcert.backend.error;

public class DomainMisMatchException extends RuntimeException{
    public DomainMisMatchException() {super();}

    public DomainMisMatchException(String message) {super(message);}
}
