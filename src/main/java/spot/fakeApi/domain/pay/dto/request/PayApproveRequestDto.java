package spot.fakeApi.domain.pay.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PayApproveRequestDto(
        @NotBlank(message = "cid 값이 누락되었습니다.")
        String cid,
        @NotBlank(message = "tid 값이 누락되었습니다.")
        String tid,
        @NotBlank(message = "partner_order_id 값이 누락되었습니다.")
        String partner_order_id,
        @NotBlank(message = "partner_user_id 값이 누락되었습니다.")
        String partner_user_id,
        @NotBlank(message = "pg_token 값이 누락되었습니다.")
        String pg_token
) {
}
