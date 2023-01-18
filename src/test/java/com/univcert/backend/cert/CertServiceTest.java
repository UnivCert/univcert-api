package com.univcert.backend.cert;

import com.univcert.backend.cert.dto.UnivAndEmailDto;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CertServiceTest {

    @Autowired CertService service;

    @Test
    @DisplayName("홈화면 try API")
    void tryOut() {
        UnivAndEmailDto test1 = new UnivAndEmailDto("홍익대학교","insi2000@mail.hongik.ac.kr");
        UnivAndEmailDto test2 = new UnivAndEmailDto("한양대학교","insi2000@mail.hongik.ac.kr");
        JSONObject jsonObject1 = service.tryOut(test1);
        JSONObject jsonObject2 = service.tryOut(test2);
        Assertions.assertEquals(jsonObject1.get("success"),true);
        Assertions.assertEquals(jsonObject2.get("success"),false);
    }
}