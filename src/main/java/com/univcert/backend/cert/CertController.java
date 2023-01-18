package com.univcert.backend.cert;

import com.univcert.backend.PropertyUtil;
import com.univcert.backend.cert.dto.CertifyDto;
import com.univcert.backend.cert.dto.MailDto;
import com.univcert.backend.cert.dto.UnivAndEmailDto;
import com.univcert.backend.error.InstanceNotFoundException;
import com.univcert.backend.error.UserNotFoundException;
import com.univcert.backend.user.dto.JoinDto;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Api(tags = "인증")
@RestController
@RequiredArgsConstructor
public class CertController {

    private final CertService certService;

    @ApiOperation(value = "메인 홈에서 API 시도해보기", notes = "어떤 사람이든 가능하게 https://open.kickbox.com/  처럼")
    @PostMapping("/try")
    public JSONObject tryApi(@RequestBody UnivAndEmailDto dto) {
        return certService.tryOut(dto);
    }

    @ApiOperation(value = "대학 메일 인증 시작", notes = "유저 토큰은 필요없고, address, univ만 주시면 됩니다  1000~9999의 인증번호 메일전송예정")
    @PostMapping("/v1/certify")
    public JSONObject sendMail(@RequestBody CertifyDto certifyDto) {
        return certService.sendMail(certifyDto);
    }

    @ApiOperation(value = "인증코드 확인", notes = "\"success\" : false 를 받았다면 학생증 인증도 있다는 걸 안내해야됩니다.\n ")
    @PostMapping("/v1/certifycode")
    public JSONObject receiveMail(@RequestBody MailDto mailDto) {
        return certService.receiveMail(mailDto);
    }
    @ApiOperation(value = "인증코드 확인", notes = "\"success\" : false 를 받았다면 학생증 인증도 있다는 걸 안내해야됩니다.\n ")
    @PostMapping("/v1/status")
    public JSONObject receiveMail(@RequestBody MailDto mailDto) {
        return certService.receiveMail(mailDto);
    }
    @ApiOperation(value = "인증코드 확인", notes = "\"success\" : false 를 받았다면 학생증 인증도 있다는 걸 안내해야됩니다.\n ")
    @PostMapping("/v1/certifiedlist")
    public JSONObject receiveMail(@RequestBody MailDto mailDto) {
        return certService.receiveMail(mailDto);
    }



    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected JSONObject handleUserNotFoundException() {
        return PropertyUtil.responseMessage("존재하지 않는 유저입니다.");
    }

    @ExceptionHandler(InstanceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected JSONObject handleInstanceNotFoundException() {return PropertyUtil.responseMessage("존재하지 않는 객체입니다.");}
}
