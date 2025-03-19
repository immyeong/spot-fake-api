package spot.fakeApi.domain.pay.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import spot.fakeApi.domain.pay.service.PayFakeApiService;

@WebMvcTest(controllers = PayFakeApiController.class)
class PayFakeApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    PayFakeApiService payFakeApiService;

    @DisplayName("결제 준비를 완료하면 redirectPcUrl, redirectMobileUrl, tid가 반환된다.")
    @Test
    void responsePayReady(){
        ///given


        ///when

        ///then
    }
}