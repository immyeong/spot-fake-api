package spot.fakeApi.domain.login.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import spot.fakeApi.domain.login.dto.response.LoginResponseDto;
import spot.fakeApi.domain.login.service.LoginFakeApiService;
import spot.fakeApi.domain.login.util.JwtUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LoginFakeApiController.class)
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    JwtUtil jwtUtil;

    @MockitoBean
    LoginFakeApiService loginService;

    @DisplayName("멤버 아이디를 받아 토큰 값을 리턴해준다.")
    @Test
    void responseToken() throws Exception {
        ///given
        Long memberId = 1L;
        String mockAccessToken = "mock-access-token";
        String mockRefreshToken = "mock-refresh-token";
        LoginResponseDto loginResponse = LoginResponseDto.create(mockAccessToken, mockRefreshToken);

        given(jwtUtil.createAccessToken(any())).willReturn(mockAccessToken);
        given(jwtUtil.createRefreshToken(any())).willReturn(mockRefreshToken);
        given(loginService.createToken(any())).willReturn(loginResponse);

        ///when ///then
        mockMvc.perform(
                        get("/api/login/token")
                                .param("memberId", String.valueOf(memberId))
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("정상적으로 처리하였습니다."));
    }

    @DisplayName("멤버 아이디가 누락되면 예외가 발생한다.")
    @Test
    void responseTokenWithoutMemberId() throws Exception {
        ///given ///when ///then
        mockMvc.perform(
                        get("/api/login/token")
                                .param("memberId", (String) null)
                )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}