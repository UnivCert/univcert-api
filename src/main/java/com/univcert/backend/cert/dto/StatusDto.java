package com.univcert.backend.cert.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class StatusDto {
    @NotBlank
    @ApiModelProperty(example = "PDJIWELFKQA3EFIEOAO1GKFGEDP", notes = "회원가입시 부여")
    private String key;

    @NotBlank
    @ApiModelProperty(example = "insi2000@mail.hongik.ac.kr")
    private String email;

    public StatusDto(String API_KEY, String email) {
        this.key = API_KEY;
        this.email = email;
    }
}
