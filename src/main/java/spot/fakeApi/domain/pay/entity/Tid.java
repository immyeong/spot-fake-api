package spot.fakeApi.domain.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class Tid {
    private String tid;

    @Setter
    private TidStatus status;

}
