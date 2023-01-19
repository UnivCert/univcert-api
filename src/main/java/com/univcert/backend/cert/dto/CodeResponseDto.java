package com.univcert.backend.cert.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CodeResponseDto {
    @NotBlank
    @ApiModelProperty(example = "PDJIWELFKQA3EFIEOAO1GKFGEDP", notes = "회원가입시 부여")
    private String API_KEY;

    @NotBlank
    @ApiModelProperty(example = "홍익대학교")
    private String univ;

    @NotBlank
    @ApiModelProperty(example = "insi2000@mail.hongik.ac.kr")
    private String email;

    @NotBlank
    @ApiModelProperty(example = "3819")
    private String code;

    public CodeResponseDto(String API_KEY, String univ, String email, String code) {
        this.API_KEY = API_KEY;
        this.univ = univ;
        this.email = email;
        this.code = code;
    }
}
