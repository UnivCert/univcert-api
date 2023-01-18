package com.univcert.backend.cert.dto;

import lombok.Getter;

@Getter
public class UnivAndEmailDto {
    private String name;
    private String email;

    public UnivAndEmailDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
