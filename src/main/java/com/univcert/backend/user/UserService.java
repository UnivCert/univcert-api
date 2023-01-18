package com.univcert.backend.user;

import com.univcert.backend.PropertyUtil;
import com.univcert.backend.error.UserNotFoundException;
import com.univcert.backend.user.dto.JoinDto;
import com.univcert.backend.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

//    @Transactional
//    public TokenDto login(EmailDto dto) {
//
//        User user = userRepository.findByEmail(dto.getEmail())
//                .orElseThrow(() -> new UserNotFoundException("가입되지 않은 ID 입니다."));
//        TokenDto tokenDto = jwtProvider.createToken(user.getEmail(), user.getId(), user.getRoles());
//        //리프레시 토큰 저장
//        RefreshToken refreshToken = RefreshToken.builder()
//                .key(user.getId())
//                .token(tokenDto.getRefreshToken())
//                .build();
//        refreshTokenRepository.save(refreshToken);
//        return tokenDto;
//    }


    @Transactional
    public JSONObject join(JoinDto dto) {
        Optional<User> existUser = userRepository.findByEmail(dto.getEmail());
        if(existUser.isPresent())
            return PropertyUtil.responseMessage("이미 가입된 이메일입니다.");

        User user = User.builder()
                .email(dto.getEmail())
                .teamName(dto.getTeam_name())
                .build();

        String API_KEY = UUID.randomUUID().toString();
        user.giveAPI_KEY(API_KEY);
        userRepository.save(user);
        return PropertyUtil.response(API_KEY);
    }

    @Transactional(readOnly = true)
    public JSONObject checkEmailAndTeamName(JoinDto dto) {
        Optional<User> existUser = userRepository.findByEmailOrTeamName(dto.getEmail(), dto.getTeam_name());
        if (existUser.isPresent())
            return PropertyUtil.responseMessage("이미 존재하는 이메일이거나 소속명입니다.");
        return PropertyUtil.response(true);
    }

    @Transactional(readOnly = true)
    public JSONObject login(JoinDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("가입되지 않은 ID 입니다."));
        UserDto userDto = new UserDto(user.getEmail(), user.getTeamName(), user.getAPI_KEY(), user.getQueryCount());
        return PropertyUtil.response(userDto);
    }

}
