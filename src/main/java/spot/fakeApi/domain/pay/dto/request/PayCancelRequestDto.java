package spot.fakeApi.domain.pay.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PayCancelRequestDto(
        @NotBlank(message = "cid 값이 누락되었습니다.")
        String cid,
        @NotBlank(message = "tid 값이 누락되었습니다.")
        String tid,
        @NotBlank(message = "cancel_amount 값이 누락되었습니다.")
        String cancel_amount,
        @NotBlank(message = "cancel_vat_amount 값이 누락되었습니다.")
        String cancel_vat_amount,
        @NotBlank(message = "cancel_available_amount 값이 누락되었습니다.")
        String cancel_available_amount
) {
}
