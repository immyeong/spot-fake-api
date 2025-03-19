package spot.fakeApi.domain.login.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import spot.fakeApi.domain.login.service.LoginService;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers = LoginController.class)
class LoginControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    LoginService loginService;

    @DisplayName("멤버 아이디를 받아 토큰 값을 리턴해준다.")
    @Test
    void test(){
        ///given

        ///when

        ///then
    }
}