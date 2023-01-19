package com.univcert.backend.cert;

import com.univcert.backend.cert.dto.CertifyDto;
import com.univcert.backend.cert.dto.UnivAndEmailDto;
import com.univcert.backend.error.UserNotFoundException;
import com.univcert.backend.user.User;
import com.univcert.backend.user.UserRepository;
import com.univcert.backend.user.UserService;
import com.univcert.backend.user.dto.JoinDto;
import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestClass;
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

    @Test
    @Order(1)
    @DisplayName("회원가입")
    @Transactional
    @Rollback(value = false)
    void join() {
        JoinDto dto = new JoinDto("착송","sis000512@naver.com");
        JSONObject obj = userService.join(dto);
        assertEquals(obj.get("success"), true);
    }

    @Test
    @Order(2)
    @DisplayName("회원 저장 테스트")
    void certifyStart() {
        user = userService.findByEmail("sis000512@naver.com");
        assertEquals(user.getEmail(),"sis000512@naver.com");
    }

    @Test
    @Order(3)
    @DisplayName("홈화면 try API, 해당 대학와 메일의 도메인이 일치하는지 체크")
    void tryOut() {
        UnivAndEmailDto test1 = new UnivAndEmailDto("홍익대학교","insi2000@mail.hongik.ac.kr");
        UnivAndEmailDto test2 = new UnivAndEmailDto("한양대학교","insi2000@mail.hongik.ac.kr");
        JSONObject jsonObject1 = certService.tryOut(test1);
        JSONObject jsonObject2 = certService.tryOut(test2);
        Assertions.assertEquals(jsonObject1.get("success"),true);
        Assertions.assertEquals(jsonObject2.get("success"),false);
    }

    @Test
    @Order(4)
    @DisplayName("메일인증 초입단계")
    void requestCertify() {
        API_KEY = user.getAPI_KEY();
        CertifyDto certifyInfo = new CertifyDto(API_KEY, "홍익대학교", "insi2000@mail.hongik.ac.kr", true);
        certService.requestCertify(certifyInfo);
    }


}