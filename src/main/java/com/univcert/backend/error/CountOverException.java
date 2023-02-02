package com.univcert.backend.error;

public class CountOverException extends RuntimeException{
    public CountOverException(String message) {
        super(message);
    }

    public CountOverException() {super();}

}
