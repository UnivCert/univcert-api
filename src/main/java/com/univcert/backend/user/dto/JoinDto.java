package com.univcert.backend.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class JoinDto {

    @ApiModelProperty(example = "소속명")
    private String team_name;
    @ApiModelProperty(example = "연락 가능한 이메일")
    private String email;

    public JoinDto(String team_name, String email) {
        this.team_name = team_name;
        this.email = email;
    }
}
