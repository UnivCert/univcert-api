package com.univcert.backend.cert.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class VerifyDto {
    @NotBlank
    @ApiModelProperty(example = "착송", notes = "회사 이름 이나 단체 이름 기입")
    private String API_KEY;

    @NotBlank
    @ApiModelProperty(example = "홍익대학교")
    private String univ;

    @NotBlank
    @ApiModelProperty(example = "insi2000@mail.hongik.ac.kr")
    private String email;

    @NotBlank
    @ApiModelProperty(value = "true 시 도메인까지 체크")
    private boolean univ_check = false;
}
