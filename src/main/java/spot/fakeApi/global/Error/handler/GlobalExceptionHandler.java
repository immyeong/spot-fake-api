package spot.fakeApi.global.Error.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import spot.fakeApi.global.Error.format.GlobalException;
import spot.fakeApi.global.response.ApiResponse;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    private static final HttpHeaders jsonHeaders;
    static {
        jsonHeaders = new HttpHeaders();
        jsonHeaders.add(HttpHeaders.CONTENT_TYPE, "application/json");
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ApiResponse<Object>> handleGlobalException (GlobalException globalException) {
        log.error("Global Exception : {}", globalException.getMessage());
        return ResponseEntity
                .status(globalException.getErrorCode().getStatus())
                .headers(jsonHeaders)
                .body(ApiResponse.fail(globalException.getErrorCode().getMessage()));
    }
}
