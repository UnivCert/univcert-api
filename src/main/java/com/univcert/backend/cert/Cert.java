package com.univcert.backend.cert;

import com.univcert.backend.BaseTimeEntity;
import com.univcert.backend.user.User;
import lombok.Builder;
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
    private int count=0;

    @Column
    private String code;

    @Column
    private boolean certified;  //메일 인증여부

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Cert(String email, String univName, String code, boolean certified) {
        this.email = email;
        this.univName = univName;
        this.code = code;
        this.certified = certified;
    }

    protected Cert() {}

    public void updateCodeAndPlusCount(String code) {
        this.code = code;
        this.count++;
    }

    public void setCertified() {
        this.certified = true;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
