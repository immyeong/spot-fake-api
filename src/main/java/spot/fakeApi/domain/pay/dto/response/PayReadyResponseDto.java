package spot.fakeApi.domain.pay.dto.response;

import lombok.Builder;

@Builder
public record PayReadyResponseDto(
        String redirectPCUrl,
        String redirectMobileUrl,
        String tid
) {

    public static PayReadyResponseDto create(String tid, String redirectMobileUrl, String redirectPCUrl) {
        return PayReadyResponseDto.builder()
                .tid(tid)
                .redirectMobileUrl(redirectMobileUrl)
                .redirectPCUrl(redirectPCUrl)
                .build();
    }
}
