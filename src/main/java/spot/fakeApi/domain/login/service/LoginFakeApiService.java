package spot.fakeApi.domain.login.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spot.fakeApi.domain.login.dto.response.LoginResponseDto;
import spot.fakeApi.domain.login.entity.Token;
import spot.fakeApi.domain.login.util.JwtUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginFakeApiService {

    private final JwtUtil jwtUtil;
    private Map<Long, Token> tokenMap = new ConcurrentHashMap<>();

    ///토큰 생성
    public LoginResponseDto createToken(String memberId) {
        long memberIdToLong = Long.parseLong(memberId);
        String accessToken = jwtUtil.createAccessToken(memberIdToLong);
        String refreshToken = jwtUtil.createRefreshToken(memberIdToLong);
        saveToken(memberIdToLong, accessToken, refreshToken);
        log.info("fake API Server : 토큰이 발급되었습니다.");

        return LoginResponseDto.create(accessToken, refreshToken);
    }

    /// 토큰 조회
    public Token findAccessToken(Long memberId) {
        return tokenMap.get(memberId);
    }

    ///토큰 저장
    private void saveToken(Long memberId, String accessToken, String refreshToken) {
        tokenMap.put(memberId, new Token(accessToken, refreshToken));
    }
}
