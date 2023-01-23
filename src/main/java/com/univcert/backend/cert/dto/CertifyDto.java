package com.univcert.backend.cert.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class CertifyDto {
    @NotBlank
    @ApiModelProperty(example = "PDJIWELFKQA3EFIEOAO1GKFGEDP", notes = "회원가입시 부여")
    private String key;

    @NotBlank
    @ApiModelProperty(example = "홍익대학교")
    private String univ;

    @NotBlank
    @ApiModelProperty(example = "insi2000@mail.hongik.ac.kr")
    private String email;

    @NotBlank
    @ApiModelProperty(value = "true 시 도메인까지 체크")
    private boolean univ_check = false;

    public CertifyDto(String key, String univ, String email, boolean univ_check) {
        this.key = key;
        this.univ = univ;
        this.email = email;
        this.univ_check = univ_check;
    }
}
