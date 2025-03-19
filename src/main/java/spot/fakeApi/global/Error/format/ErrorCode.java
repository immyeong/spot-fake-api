package spot.fakeApi.global.Error.format;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_JWT(HttpStatus.UNAUTHORIZED, "JWT가 없습니다."),
    EXPIRED_JWT(HttpStatus.UNAUTHORIZED, "만료된 JWT 입니다."),
    INVALID_JWT(HttpStatus.UNAUTHORIZED, "잘못된 JWT 입니다."),
    UNSUPPORTED_JWT(HttpStatus.UNAUTHORIZED, "지원하지 않는 버전의 JWT 입니다."),
    ILLEGAL_JWT(HttpStatus.UNAUTHORIZED, " 잘못된 JWT 입니다."),
    UNKNOWN_JWT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알려지지 않은 JWT 에러 입니다."),

    ALREADY_COMPLETE_TID(HttpStatus.BAD_REQUEST, "이미 결제 준비된 건입니다."),
    ALREADY_COMPLETE_PGTOKEN(HttpStatus.BAD_REQUEST, "이미 결제 승인된 건입니다."),
    INVALID_TID(HttpStatus.BAD_REQUEST, "일치하는 결제건이 없습니다."),
    UNMATCH_AMOUNT(HttpStatus.BAD_REQUEST, "결제된 상품 금액과 일치하지 않습니다.")
    ;
    private final HttpStatus status;
    private final String message;
}
