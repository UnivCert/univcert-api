package com.univcert.backend.cert.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
public class ResponseListForm {
    private String email;
    private String univName;
    private int count;
    private boolean certified;  //메일 인증여부

    @Builder
    public ResponseListForm(String email, String univName, int count, boolean certified) {
        this.email = email;
        this.univName = univName;
        this.count = count;
        this.certified = certified;
    }
}
