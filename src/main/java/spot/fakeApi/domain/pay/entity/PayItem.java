package spot.fakeApi.domain.pay.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PayItem {

    private String partnerOrderId;
    private String partnerUserId;
    private String quantity;
    private String totalAmount;
    private String itemName;
}
