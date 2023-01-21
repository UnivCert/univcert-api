package com.univcert.backend.cert.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class API_KEYDto {
    @ApiModelProperty("API_KEY")
    private String key;
}
