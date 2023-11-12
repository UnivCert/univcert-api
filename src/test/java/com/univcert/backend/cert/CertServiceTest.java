package com.univcert.backend.cert;

import com.univcert.backend.cert.dto.CertifyDto;
import com.univcert.backend.cert.dto.CodeResponseDto;
import com.univcert.backend.cert.dto.StatusDto;
import com.univcert.backend.cert.dto.UnivAndEmailDto;
import com.univcert.backend.error.CertNotFoundException;
import com.univcert.backend.error.DomainMisMatchException;
import com.univcert.backend.user.User;
import com.univcert.backend.user.UserService;
import com.univcert.backend.user.dto.JoinDto;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CertServiceTest {
    @Autowired UserService userService;
    @Autowired CertService certService;
    @Autowired CertController certController;
    private User user;
    private String API_KEY = "df6ea145-4134-40a3-a298-764cd7d5d7bb";
    private static final String teamEmail = "sis000512@naver.com";
    private static final String certifyEmail = "insi2000@mail.hongik.ac.kr";
    private static final String uncertifiedEmail = "sis000512@naver.com";
    private static final String uncertifyEmail = "thddlstj@mail.hanyang.ac.kr";
    private static final String teamName = "착송";
    private static final String univName = "홍익대학교";

//    @Test
//    @Order(1)
//    @DisplayName("회원가입")
//    @Transactional
//    @Rollback(value = false)
//    void join() {
//        JoinDto dto = new JoinDto(teamName, teamEmail);
//        JSONObject obj = userService.join(dto);
//        assertEquals(obj.get("success"), true);
//    }

//    @Test
//    @Order(2)
//    @DisplayName("회원 저장 테스트")
//    void certifyStart() {
//        user = userService.findByEmail(teamEmail);
//        API_KEY = user.getAPI_KEY();
//        assertEquals(user.getEmail(), teamEmail);
//    }

//    @Test
//    @Order(3)
//    @DisplayName("홈화면 try API, 해당 대학와 메일의 도메인이 일치하는지 체크")
//    void tryOut() throws DomainMisMatchException {
//        UnivAndEmailDto test1 = new UnivAndEmailDto(univName, certifyEmail);
//        UnivAndEmailDto test2 = new UnivAndEmailDto("한양대학교", certifyEmail);
//        JSONObject jsonObject1 = certService.tryOut(test1);
//        Assertions.assertThrows(DomainMisMatchException.class, () -> certService.tryOut(test2));
//        Assertions.assertEquals(true,jsonObject1.get("success"));
//    }
//
//    @Test
//    @Order(4)
//    @DisplayName("메일인증 초입단계, 메일 전송 여부")
//    @Transactional
//    @Rollback(value = false)
//    void requestCertify() {
//        CertifyDto certifyInfo = new CertifyDto(API_KEY, univName, uncertifiedEmail, false);
//        MailForm mailForm  = certService.checkErrorAndMakeForm(certifyInfo);
//        certService.sendMail(mailForm);
//        JSONObject response = certController.sendMail(certifyInfo);
//        assertEquals(true, response.get("success"));
//        String code = certService.getCode(certifyInfo.getEmail());
//        assertEquals(code.length(), 4);
//    }
//
//    @Test
//    @Order(5)
//    @DisplayName("메일의 인증번호 체크")
//    @Transactional
//    @Rollback(value = false)
//    void responseCode() {
//        String code = certService.getCode(certifyEmail);
//        CodeResponseDto codeDto = new CodeResponseDto(API_KEY, univName, certifyEmail, code);
//        JSONObject jsonObject = certService.receiveMail(codeDto);
//        assertEquals(jsonObject.get("success"), true);
//    }
//
//    @Test
//    @Order(6)
//    @DisplayName("인증 verified = true 여부")
//    void showVerifiedStatus() {
//        Cert cert = certService.getCert(certifyEmail);
//        assertEquals(true, cert.isCertified());
//    }
//
//    @Test
//    @Order(7)
//    @DisplayName("인증 이력 조회")
//    void showStatus() {
//        JSONObject certifiedStatus = certService.getStatus(new StatusDto(API_KEY, certifyEmail));
//        Assertions.assertThrows(CertNotFoundException.class, ()->certService.getStatus(new StatusDto(API_KEY, uncertifyEmail)));
//        assertEquals(true, certifiedStatus.get("success"));
//    }

//    @Test
//    @Order(8)
//    @DisplayName("인증된 사람 리스트 출력")
//    void showList() {
//        JSONObject certifiedList = certService.getCertifiedList(API_KEY);
//        System.out.println(certifiedList.toJSONString());
//        assertEquals(false,certifiedList.get("data").equals(Object.class));
//    }

    @Test
    @Order(9)
    @DisplayName("인증 이력 초기화 [유저 1명]")
    void showList() {
        certService.clear(API_KEY, certifyEmail);
    }


}