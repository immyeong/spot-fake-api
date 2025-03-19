package spot.fakeApi.domain.pay.dto.response;

import lombok.Builder;

@Builder
public record PayCancelResponseDto(
        String itemName,
        String totalAmount
) {

    public static PayCancelResponseDto create(String itemName, String totalAmount) {
        return PayCancelResponseDto.builder()
                .itemName(itemName)
                .totalAmount(totalAmount)
                .build();
    }
}
