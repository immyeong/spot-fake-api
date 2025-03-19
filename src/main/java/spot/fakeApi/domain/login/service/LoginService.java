package spot.fakeApi.domain.login.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spot.fakeApi.domain.login.dto.response.LoginResponseDto;
import spot.fakeApi.domain.login.token.Token;
import spot.fakeApi.domain.login.token.util.JwtUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtUtil jwtUtil;
    private Map<Long, Token> tokenMap = new ConcurrentHashMap<>();

    ///토큰 생성
    public LoginResponseDto createToken(Long memberId) {
        String accessToken = jwtUtil.createAccessToken(memberId);
        String refreshToken = jwtUtil.createRefreshToken(memberId);
        saveToken(memberId, accessToken, refreshToken);

        return LoginResponseDto.create(accessToken, refreshToken);
    }

    ///토큰 저장
    private void saveToken(Long memberId, String accessToken, String refreshToken) {
        tokenMap.put(memberId, new Token(accessToken, refreshToken));
    }
}
