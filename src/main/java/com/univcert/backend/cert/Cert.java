package com.univcert.backend.cert;

import com.univcert.backend.BaseTimeEntity;
import com.univcert.backend.user.User;
import lombok.Getter;


import javax.persistence.*;

@Getter
@Entity
public class Cert extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cert_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String univName;

    @Column
    private int count;

    @Column
    private String code;

    @Column
    private boolean certified;  //메일 인증여부

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Cert(String email, String code, boolean certified) {
        this.email = email;
        this.code = code;
        this.certified = certified;
    }

    protected Cert() {}

    public void updateKey(String code) {
        this.code = code;
    }

    public void setCertified() {
        this.certified = true;
    }
}
