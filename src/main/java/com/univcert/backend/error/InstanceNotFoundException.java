package com.univcert.backend.error;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstanceNotFoundException extends  RuntimeException {

    public InstanceNotFoundException() {
        super();
    }
    public InstanceNotFoundException(String s) {
        super(s);
    }
}
