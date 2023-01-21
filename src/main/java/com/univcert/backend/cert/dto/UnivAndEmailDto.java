package com.univcert.backend.cert.dto;

import lombok.Getter;

@Getter
public class UnivAndEmailDto {
    private String univName;
    private String email;

    public UnivAndEmailDto(String univName, String email) {
        this.univName = univName;
        this.email = email;
    }
}
