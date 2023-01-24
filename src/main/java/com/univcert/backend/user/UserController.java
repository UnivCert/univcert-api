package com.univcert.backend.user;

import com.univcert.backend.error.ApiDocumentResponse;
import com.univcert.backend.user.dto.JoinDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "유저")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입", notes = "성공 시 \n{\n" +
            "  \"data\": \"df6ea145-4134-40a3-a298-764cd7d5d7bb\",\n" +
            "  \"success\": true\n}" +
            "\n 실패시\n" +
            " \"success\": false,\n" +
            "  \"message\": \"이미 존재하는 이메일이거나 소속명입니다.\"\n" +
            "}")
    @PostMapping("/join")
    public JSONObject join(@RequestBody JoinDto dto) {
        return userService.join(dto);
    }

    @ApiDocumentResponse
    @ApiOperation(value = "이메일, 소속명 중복체크", notes = "성공 시 success true 실패 시 success false 랑 이미 존재하는 이메일이거나 소속명입니다. 출력")
    @PostMapping("/check")
    public JSONObject checkJoin(@RequestBody JoinDto dto) {
        return userService.checkEmailAndTeamName(dto);
    }

    @ApiOperation(value = "이메일과 소속명으로 로그인", notes = "성공시 유저 정보를 보내드립니다. 세션이나 쿠키 안쓸거라 그냥 이걸 바로 홈에 띄울 예정 이메일, 소속명, API_KEY, queryCount(일별 시도 가능 횟수)" +
            "{\n" +
            "  \"data\": {\n" +
            "    \"email\": \"sis000512@naver.com\",\n" +
            "    \"teamName\": \"착송\",\n" +
            "    \"queryCount\": 0,\n" +
            "    \"api_KEY\": \"df6ea145-4134-40a3-a298-764cd7d5d7bb\"\n" +
            "  },\n" +
            "  \"success\": true\n" +
            "}")
    @PostMapping("/login")
    public JSONObject login(@RequestBody JoinDto dto) {
        return userService.login(dto);
    }

}
