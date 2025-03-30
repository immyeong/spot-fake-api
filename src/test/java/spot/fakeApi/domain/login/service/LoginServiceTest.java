package spot.fakeApi.domain.login.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import spot.fakeApi.domain.login.dto.response.LoginResponseDto;
import spot.fakeApi.domain.login.entity.Token;
import spot.fakeApi.domain.login.util.JwtUtil;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    LoginFakeApiService loginService;

    @Autowired
    JwtUtil jwtUtil;

    @DisplayName("멤버 아이디가 있으면 어세스토큰, 리프래쉬 토큰 값을 생성한다.")
    @Test
    void createToken(){
        ///given
        Long memberId = 1L;

        ///when
        LoginResponseDto token = loginService.createToken(String.valueOf(memberId));

        ///then
        assertThat(token.accessToken()).isNotNull();
    }

    @DisplayName("멤버 아이디로 해당하는 토큰값을 조회할 수 있다.")
    @Test
    void test(){
        ///given
        Long memberId = 1L;
        LoginResponseDto token = loginService.createToken(String.valueOf(memberId));

        ///when
        Token findToken = loginService.findAccessToken(memberId);

        ///then
        assertThat(findToken.getAccessToken()).isEqualTo(token.accessToken());
    }
}