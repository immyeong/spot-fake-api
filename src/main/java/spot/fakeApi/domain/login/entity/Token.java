package spot.fakeApi.domain.login.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Token {

    private String accessToken;
    private String refreshToken;
}
