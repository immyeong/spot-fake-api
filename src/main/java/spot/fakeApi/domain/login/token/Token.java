package spot.fakeApi.domain.login.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Token {

    private String accessToken;
    private String refreshToken;
}
