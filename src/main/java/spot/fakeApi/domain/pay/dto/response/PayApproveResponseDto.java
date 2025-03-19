package spot.fakeApi.domain.pay.dto.response;

import lombok.Builder;
import spot.fakeApi.domain.pay.entity.PayItem;

@Builder
public record PayApproveResponseDto(
        String itemName,
        String partnerOrderId,
        String partnerUserId,
        String totalAmount,
        String quantity
) {

    public static PayApproveResponseDto fromPayItem(PayItem payItem) {
        return PayApproveResponseDto.builder()
                .itemName(payItem.getItemName())
                .partnerOrderId(payItem.getPartnerOrderId())
                .partnerUserId(payItem.getPartnerUserId())
                .totalAmount(payItem.getTotalAmount())
                .quantity(payItem.getQuantity())
                .build();
    }
}
