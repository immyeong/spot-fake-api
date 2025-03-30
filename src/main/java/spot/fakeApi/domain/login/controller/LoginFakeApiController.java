package spot.fakeApi.domain.login.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spot.fakeApi.domain.login.dto.response.LoginResponseDto;
import spot.fakeApi.domain.login.service.LoginFakeApiService;
import spot.fakeApi.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fake-api/login")
public class LoginFakeApiController {

    private final LoginFakeApiService loginService;

    @GetMapping("/token")
    public ApiResponse<LoginResponseDto> responseToken(@RequestParam @NotBlank(message = "멤버 아이디는 필수 값입니다.") String memberId) {
        LoginResponseDto loginResponseDto = loginService.createToken(memberId);
        return ApiResponse.success(loginResponseDto);
    }
}
