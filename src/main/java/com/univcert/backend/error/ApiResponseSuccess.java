package com.univcert.backend.error;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ApiResponseSuccess {
    @ApiModelProperty(value = "true", example = "true")
    private boolean success;
}
