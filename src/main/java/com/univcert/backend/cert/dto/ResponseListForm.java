package com.univcert.backend.cert.dto;

import lombok.Builder;
import lombok.Getter;


@Getter
public class ResponseListForm {
    private String email;
    private String univName;
    private String certified_date;
    private int count;
    private boolean certified;  //메일 인증여부

    @Builder
    public ResponseListForm(String email, String univName, String certified_date, int count, boolean certified) {
        this.email = email;
        this.univName = univName;
        this.certified_date = certified_date;
        this.count = count;
        this.certified = certified;
    }
}
