package spot.fakeApi.domain.login.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import spot.fakeApi.domain.login.dto.response.LoginResponseDto;
import spot.fakeApi.domain.login.service.LoginService;
import spot.fakeApi.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/token")
    public ApiResponse<LoginResponseDto> responseToken(@RequestParam @NotBlank(message = "멤버 아이디는 필수 값입니다.") Long memberId) {
        LoginResponseDto loginResponseDto = loginService.createToken(memberId);
        return ApiResponse.success(loginResponseDto);
    }
}
