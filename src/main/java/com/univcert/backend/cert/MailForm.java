package com.univcert.backend.cert;

import lombok.Getter;

@Getter
public class MailForm {
    private String email;
    private String teamName;
    private String code;

    public MailForm(String email, String teamName, String code) {
        this.email = email;
        this.teamName = teamName;
        this.code = code;
    }
}
