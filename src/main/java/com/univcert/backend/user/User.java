package com.univcert.backend.user;

import com.univcert.backend.BaseTimeEntity;
import com.univcert.backend.cert.Cert;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@SequenceGenerator(name = "User_SEQ_GEN",sequenceName = "User_SEQ")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "User_SEQ")
    @Column(name = "user_id")
    private Long id;

    @Column
    private String email;

    @Column
    private String teamName;

    @Column
    private String API_KEY;

    @Column
    private int queryCount;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Cert> certList = new ArrayList<>();

    @Builder
    public User(String email, String teamName) {
        this.email = email;
        this.teamName = teamName;
    }

    protected User() {}

    public void giveAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }
}

