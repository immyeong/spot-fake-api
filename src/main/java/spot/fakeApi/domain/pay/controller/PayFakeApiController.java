package spot.fakeApi.domain.pay.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spot.fakeApi.domain.pay.dto.request.PayApproveRequestDto;
import spot.fakeApi.domain.pay.dto.request.PayCancelRequestDto;
import spot.fakeApi.domain.pay.dto.request.PayReadyRequestDto;
import spot.fakeApi.domain.pay.dto.response.PayApproveResponseDto;
import spot.fakeApi.domain.pay.dto.response.PayCancelResponseDto;
import spot.fakeApi.domain.pay.dto.response.PayReadyResponseDto;
import spot.fakeApi.domain.pay.service.PayFakeApiService;
import spot.fakeApi.global.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fake-api/pay")
public class PayFakeApiController {

    private final PayFakeApiService payApiService;

    ///결제 준비
    @PostMapping("/ready")
    public ApiResponse<PayReadyResponseDto> responsePayReady(@Valid @RequestBody PayReadyRequestDto payReadyRequestDto) {
        PayReadyResponseDto payReadyResponseDto = payApiService.responsePayReady(payReadyRequestDto);
        return ApiResponse.success(payReadyResponseDto);
    }

    ///pcRedirectUrl 처리
    @GetMapping("/pc/{redirectUrl}")
    public ApiResponse<String> responsePgTokenAboutPc(@PathVariable("redirectUrl") String redirectUrl) {
        String pgToken = payApiService.vaildRedirectUrl(redirectUrl, "pc");
        return ApiResponse.success(pgToken);
    }

    ///mobileRedirectUrl 처리
    @GetMapping("/mobile/{redirectUrl}")
    public ApiResponse<String> responsePgTokenAboutMobile(@PathVariable("redirectUrl") String redirectUrl) {
        String pgToken = payApiService.vaildRedirectUrl(redirectUrl, "mobile");
        return ApiResponse.success(pgToken);
    }

    ///결제 승인
    @PostMapping("/approve")
    public ApiResponse<PayApproveResponseDto> responsePayApprove(@Valid @RequestBody PayApproveRequestDto payApproveRequestDto) {
        PayApproveResponseDto payApproveResponseDto = payApiService.responsePayApprove(payApproveRequestDto);
        return ApiResponse.success(payApproveResponseDto);
    }

    /// 결제 취소
    @PostMapping("/cancel")
    public ApiResponse<PayCancelResponseDto> responsePayCancel(@Valid @RequestBody PayCancelRequestDto payCancelRequestDto) {
        PayCancelResponseDto payCancelResponseDto = payApiService.responsePayCancel(payCancelRequestDto);
        return ApiResponse.success(payCancelResponseDto);
    }
}
