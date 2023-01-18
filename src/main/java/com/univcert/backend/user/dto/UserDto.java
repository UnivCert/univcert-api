package com.univcert.backend.user.dto;

import lombok.Getter;

import javax.persistence.Column;

@Getter
public class UserDto  {
    private String email;
    private String teamName;
    private String API_KEY;
    private int queryCount;

    public UserDto(String email, String teamName, String API_KEY, int queryCount) {
        this.email = email;
        this.teamName = teamName;
        this.API_KEY = API_KEY;
        this.queryCount = queryCount;
    }
}
