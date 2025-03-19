package spot.fakeApi.domain.login.token.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spot.fakeApi.domain.login.member.Member;
import spot.fakeApi.global.Error.format.ErrorCode;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private final SecretKey secretKey;
    @Value("${spring.jwt.access.token}")
    private long ACCESS_TOKEN_EXPIRE_TIME;
    @Value("${spring.jwt.refresh.token}")
    private long REFRESH_TOKEN_EXPIRE_TIME;

    public JwtUtil(@Value("${spring.jwt.secretKey}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
    }

    public ErrorCode validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return null;
        } catch (ExpiredJwtException e) {
            return ErrorCode.EXPIRED_JWT;
        } catch (io.jsonwebtoken.security.SignatureException | SecurityException | MalformedJwtException e) {
            return ErrorCode.INVALID_JWT;
        } catch (UnsupportedJwtException e) {
            return ErrorCode.UNSUPPORTED_JWT;
        } catch (IllegalArgumentException e) {
            return ErrorCode.ILLEGAL_JWT;
        } catch (Exception e) {
            log.error("그 외의 에러가 났습니다. 에러 내용:", e);
            return ErrorCode.UNKNOWN_JWT_ERROR;
        }
    }

    public String createAccessToken(Long memberId){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(String.valueOf(memberId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshToken(Long memberId){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(String.valueOf(memberId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(secretKey)
                .compact();
    }

}
