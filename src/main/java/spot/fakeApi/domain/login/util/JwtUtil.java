package spot.fakeApi.domain.login.util;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import spot.fakeApi.global.Error.format.ErrorCode;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
@Slf4j
public class JwtUtil {

    private final SecretKey secretKey;
    private final long accessTokenExpireTime;
    private final long refreshTokenExpireTime;

    public JwtUtil(
            @Value("${spring.jwt.secretKey}") String secretKey,
            @Value("${spring.jwt.access.token}") long accessTokenExpireTime,
            @Value("${spring.jwt.refresh.token}") long refreshTokenExpireTime
    ) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretKey));
        this.accessTokenExpireTime = accessTokenExpireTime;
        this.refreshTokenExpireTime = refreshTokenExpireTime;
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
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpireTime))
                .signWith(secretKey)
                .compact();
    }

    public String createRefreshToken(Long memberId){
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(String.valueOf(memberId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpireTime))
                .signWith(secretKey)
                .compact();
    }

}
