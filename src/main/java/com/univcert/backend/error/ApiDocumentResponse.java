package com.univcert.backend.error;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "API 호출 성공",response = ApiResponseSuccess.class),
        @ApiResponse(code = 404, message = "API 호출 실패",response = ApiResponseFail.class)
})
public @interface ApiDocumentResponse {
}