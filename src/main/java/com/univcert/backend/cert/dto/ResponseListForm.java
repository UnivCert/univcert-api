package com.univcert.backend.cert.dto;

import com.univcert.backend.cert.Cert;
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

    public ResponseListForm(Cert cert) {
        this.email = cert.getEmail();
        this.univName = cert.getUnivName();
        this.certified_date = cert.getCreatedDate().toString();
        this.count = cert.getCount();
        this.certified = cert.isCertified();
    }
}
