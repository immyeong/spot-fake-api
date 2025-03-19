package spot.fakeApi.domain.pay.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PayReadyRequestDto(
        @NotBlank(message = "cid 값이 누락되었습니다.")
        String cid,
        @NotBlank(message = "partner_order_id 값이 누락되었습니다.")
        String partner_order_id,
        @NotBlank(message = "partner_user_id 값이 누락되었습니다.")
        String partner_user_id,
        @NotBlank(message = "item_name 값이 누락되었습니다.")
        String item_name,
        @NotBlank(message = "quantity 값이 누락되었습니다.")
        String quantity,
        @NotBlank(message = "total_amount 값이 누락되었습니다.")
        String total_amount,
        @NotBlank(message = "approval_url 값이 누락되었습니다.")
        String approval_url,
        @NotBlank(message = "fail_url 값이 누락되었습니다.")
        String fail_url,
        @NotBlank(message = "cancel_url 값이 누락되었습니다.")
        String cancel_url

) {
}
