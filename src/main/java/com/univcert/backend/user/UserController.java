package com.univcert.backend.user;

import com.univcert.backend.user.dto.JoinDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/join")
    public JSONObject join(@RequestBody JoinDto dto) {
        return userService.join(dto);
    }

    @ApiOperation(value = "이메일, 소속명 중복체크", notes = "이메일 중복 시 에는 하단과 같이 반환됩니다 false면 가입 불가능한 이메일인거로 생각하시면 됩니다 \n{\n" +
            "  \"success\": false,\n" +
            "  \"message\": \"이미 존재하는 이메일이거나 소속명입니다.\"\n" +
            "}")
    @PostMapping("/check")
    public JSONObject checkJoin(@RequestBody JoinDto dto) {
        return userService.checkEmailAndTeamName(dto);
    }

    @ApiOperation(value = "이메일과 소속명", notes = "성공시 유저 정보를 보내드립니다. 세션이나 쿠키 안쓸거라 그냥 이걸 바로 홈에 띄울 예정 이메일, 소속명, API_KEY, queryCount(일별 시도 가능 횟수)")
    @PostMapping("/login")
    public JSONObject login(@RequestBody JoinDto dto) {
        return userService.login(dto);
    }

}
