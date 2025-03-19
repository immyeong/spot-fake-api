package spot.fakeApi.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private String status;
    private String message;
    private T data;

    public static<T> ApiResponse<T> success (T data) {
        return new ApiResponse<>("success", "정상적으로 처리하였습니다.", data);
    }

    public static <T> ApiResponse<T> fail (String message) {
        return new ApiResponse<>("fail", message, null);
    }
}
