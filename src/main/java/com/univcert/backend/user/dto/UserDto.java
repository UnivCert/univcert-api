package com.univcert.backend.user.dto;

import lombok.Getter;

@Getter
public class UserDto  {
    private String email;
    private String teamName;
    private String key;
    private int queryCount;

    public UserDto(String email, String teamName, String API_KEY, int queryCount) {
        this.email = email;
        this.teamName = teamName;
        this.key = API_KEY;
        this.queryCount = queryCount;
    }
}
