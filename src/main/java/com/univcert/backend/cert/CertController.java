package com.univcert.backend.cert;

import com.univcert.backend.PropertyUtil;
import com.univcert.backend.cert.dto.*;
import com.univcert.backend.error.*;
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

    @ApiDocumentResponse
    @ApiOperation(value = "메인 홈에서 API 시도해보기", notes = "어떤 사람이든 가능하게 https://open.kickbox.com/  처럼" +
            "{(sis000512@naver.com, 홍익대학교면?)\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"대학과 일치하지 않는 메일 도메인입니다.\"\n" +
            "}" +
            "{(insi2000@mail.hongik.ac.kr, 홍익대학교면?)\n" +
            "  \"success\": true,\n}")
    @PostMapping("/try")
    public JSONObject tryApi(@RequestBody UnivAndEmailDto dto) {
        return certService.tryOut(dto);
    }

    @ApiDocumentResponse
    @ApiOperation(value = "메인홈에서 이 대학교 명이 서버에 존재하는 학교인지 체크")
    @PostMapping("/checkuniv")
    public JSONObject sendMail(@RequestBody UnivDto univDto) {
        return certService.validateUnivName(univDto.getUnivName());
    }

    @ApiDocumentResponse
    @ApiOperation(value = "대학 메일 인증 시작", notes = "프론트단에서 이메일형식으로 잘 보내는지 체킹해주셈")
    @PostMapping("/v1/certify")
    public JSONObject sendMail(@RequestBody CertifyDto certifyDto) {
        MailForm mailForm = certService.checkErrorAndMakeForm(certifyDto);
        certService.sendMail(mailForm);  /** 비동기 처리 !! 속도 20배 향상**/
        return PropertyUtil.response(true);
    }

    @ApiDocumentResponse
    @ApiOperation(value = "인증코드 확인")
    @PostMapping("/v1/certifycode")
    public JSONObject receiveMail(@RequestBody CodeResponseDto codeDto) {
        return certService.receiveMail(codeDto);
    }

    @ApiDocumentResponse
    @ApiOperation(value = "인증여부 확인", notes = "success 여부와 인증일자 반환됨")
    @PostMapping("/v1/status")
    public JSONObject getStatus(@RequestBody StatusDto statusDto) {
        return certService.getStatus(statusDto);
    }

    @ApiOperation(value = "인증된 유저 목록 출력")
    @PostMapping("/v1/certifiedlist")
    public JSONObject getCertifiedList(@RequestBody API_KEYDto dto) {
        return certService.getCertifiedList(dto.getKey());
    }


    @ExceptionHandler(UnivNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected JSONObject handleUnivNotFoundException() {return PropertyUtil.responseMessage("서버에 존재하지 않는 대학명입니다. univ_check 값을 false로 바꿔서 진행해주세요.");}

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    protected JSONObject handleUserNotFoundException() {return PropertyUtil.responseMessage("존재하지 않는 기관명 or 이메일입니다.");}

    @ExceptionHandler(InstanceNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected JSONObject handleInstanceNotFoundException() {return PropertyUtil.responseMessage("서버에 존재하지 않는 이메일 값에 요청하고 있습니다.");}

    @ExceptionHandler(AlreadyCertifiedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected JSONObject handleAlreadyFinishException() {return PropertyUtil.responseMessage("이미 완료된 요청입니다.");}

    @ExceptionHandler(CertNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected JSONObject handleCertNotFoundException() {return PropertyUtil.responseMessage("인증 요청 이력이 존재하지 않습니다.");}

    @ExceptionHandler(CountOverException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected JSONObject handleCountOverException() {return PropertyUtil.responseMessage("일일 시도 가능 횟수 초과입니다.");}

    @ExceptionHandler(DomainMisMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected JSONObject handleDomainMisMatchException() {return PropertyUtil.responseMessage("대학과 일치하지 않는 메일 도메인입니다.");}

    @ExceptionHandler(ApiNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected JSONObject handleApiException() {return PropertyUtil.responseMessage("존재하지 않는 API_KEY입니다.");}
}
