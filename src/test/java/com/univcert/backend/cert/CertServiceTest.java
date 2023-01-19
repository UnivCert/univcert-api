package com.univcert.backend.cert;

import com.univcert.backend.cert.dto.CertifyDto;
import com.univcert.backend.cert.dto.CodeResponseDto;
import com.univcert.backend.cert.dto.UnivAndEmailDto;
import com.univcert.backend.user.User;
import com.univcert.backend.user.UserService;
import com.univcert.backend.user.dto.JoinDto;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CertServiceTest {
    @Autowired UserService userService;
    @Autowired CertService certService;
    private User user;
    private String API_KEY;
    private static final String teamEmail = "sis000512@naver.com";
    private static final String certifyEmail = "insi2000@mail.hongik.ac.kr";
    private static final String teamName = "착송";
    private static final String univName = "홍익대학교";

    @Test
    @Order(1)
    @DisplayName("회원가입")
    @Transactional
    @Rollback(value = false)
    void join() {
        JoinDto dto = new JoinDto(teamName, teamEmail);
        JSONObject obj = userService.join(dto);
        assertEquals(obj.get("success"), true);
    }

    @Test
    @Order(2)
    @DisplayName("회원 저장 테스트")
    void certifyStart() {
        user = userService.findByEmail(teamEmail);
        API_KEY = user.getAPI_KEY();
        assertEquals(user.getEmail(), teamEmail);
    }

    @Test
    @Order(3)
    @DisplayName("홈화면 try API, 해당 대학와 메일의 도메인이 일치하는지 체크")
    void tryOut() {
        UnivAndEmailDto test1 = new UnivAndEmailDto(univName, certifyEmail);
        UnivAndEmailDto test2 = new UnivAndEmailDto("한양대학교", certifyEmail);
        JSONObject jsonObject1 = certService.tryOut(test1);
        JSONObject jsonObject2 = certService.tryOut(test2);
        Assertions.assertEquals(true,jsonObject1.get("success"));
        Assertions.assertEquals(false,jsonObject2.get("success"));
    }

    @Test
    @Order(4)
    @DisplayName("메일인증 초입단계, 메일 전송 여부")
    @Transactional
    @Rollback(value = false)
    void requestCertify() {
        CertifyDto certifyInfo = new CertifyDto(API_KEY, univName, certifyEmail, true);
        JSONObject response = certService.requestCertify(certifyInfo);
        assertEquals(true, response.get("success"));
        String code = certService.getCode(certifyInfo.getEmail());
        assertEquals(code.length(), 4);
    }

    @Test
    @Order(5)
    @DisplayName("메일의 인증번호 체크")
    @Transactional
    @Rollback(value = false)
    void responseCode() {
        String code = certService.getCode(certifyEmail);
        CodeResponseDto codeDto = new CodeResponseDto(API_KEY, univName, certifyEmail, code);
        JSONObject jsonObject = certService.receiveMail(codeDto);
        assertEquals(jsonObject.get("success"), true);
    }

    @Test
    @Order(6)
    @DisplayName("인증 verified = true 여부")
    void showVerifiedStatus() {
        Cert cert = certService.getCert(certifyEmail);
        assertEquals(true, cert.isCertified());
    }




}