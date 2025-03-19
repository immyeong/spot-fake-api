package spot.fakeApi.domain.login.dto.response;

import lombok.Builder;

@Builder
public record LoginResponseDto(
        String refreshToken,
        String accessToken
) {

    public static LoginResponseDto create(String accessToken, String refreshToken) {
        return LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
