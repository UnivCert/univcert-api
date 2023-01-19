package com.univcert.backend.error;

public class CertNotFoundException extends RuntimeException{
    public CertNotFoundException(String message) {
        super(message);
    }
    public CertNotFoundException() {
        super();
    }
}
