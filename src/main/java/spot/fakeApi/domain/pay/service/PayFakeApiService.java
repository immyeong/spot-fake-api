package spot.fakeApi.domain.pay.service;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spot.fakeApi.domain.pay.dto.request.PayApproveRequestDto;
import spot.fakeApi.domain.pay.dto.request.PayCancelRequestDto;
import spot.fakeApi.domain.pay.dto.request.PayReadyRequestDto;
import spot.fakeApi.domain.pay.dto.response.PayApproveResponseDto;
import spot.fakeApi.domain.pay.dto.response.PayCancelResponseDto;
import spot.fakeApi.domain.pay.dto.response.PayReadyResponseDto;
import spot.fakeApi.domain.pay.entity.PayItem;
import spot.fakeApi.domain.pay.entity.Tid;
import spot.fakeApi.domain.pay.entity.TidStatus;
import spot.fakeApi.global.Error.format.ErrorCode;
import spot.fakeApi.global.Error.format.GlobalException;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class PayFakeApiService {

    private Map<String, Tid> pcUrlTid = new ConcurrentHashMap<>();
    private Map<String, Tid> mobileUrlTid = new ConcurrentHashMap<>();
    private Map<String, Integer> pgTokenMap = new ConcurrentHashMap<>();
    private Map<String, PayItem> payItemMap = new ConcurrentHashMap<>();


    @Value("${fake.api.redirect.domain}")
    private String redirectDomain;

    public PayReadyResponseDto responsePayReady(PayReadyRequestDto payReadyRequestDto) {
        String tid = createTid();
        String pcUrl = createRedirectURl("pc");
        String mobileUrl = createRedirectURl("mobile");
        PayItem payItem = createPayItem(payReadyRequestDto.partner_order_id(), payReadyRequestDto.partner_user_id(), payReadyRequestDto.item_name(), payReadyRequestDto.total_amount(), payReadyRequestDto.quantity());

        saveInfo(tid, pcUrl, mobileUrl, payItem);
        log.info("fake API Server : 결제 준비가 성공했습니다.");
        return PayReadyResponseDto.create(tid, mobileUrl, pcUrl);
    }

    public PayApproveResponseDto responsePayApprove(@Valid PayApproveRequestDto payApproveRequestDto) {
        String pgToken = payApproveRequestDto.pg_token();
        Integer status = pgTokenMap.get(pgToken);
        if (status == 1) {
            throw new GlobalException(ErrorCode.ALREADY_COMPLETE_PGTOKEN);
        }
        PayItem payItem = payItemMap.get(payApproveRequestDto.tid());

        if(payItem == null) throw new GlobalException(ErrorCode.INVALID_TID);
        pgTokenMap.put(pgToken, 1);

        log.info("fake API Server : 결제 승인이 성공했습니다.");
        return PayApproveResponseDto.fromPayItem(payItem);
    }

    public PayCancelResponseDto responsePayCancel(PayCancelRequestDto payCancelRequestDto) {
        PayItem payItem = payItemMap.get(payCancelRequestDto.tid());
        if (payItem == null) {
            throw new GlobalException(ErrorCode.INVALID_TID);
        }
        if(!payItem.getTotalAmount().equals(payCancelRequestDto.cancel_amount())) {
            throw new GlobalException(ErrorCode.UNMATCH_AMOUNT);
        }

        payItemMap.remove(payCancelRequestDto.tid());

        log.info("fake API Server : 결제가 실패되었습니다.");
        return PayCancelResponseDto.create(payItem.getItemName(), payItem.getTotalAmount());
    }

    public String vaildRedirectUrl(String redirectUrl, String domain) {
        Tid tid = null;
        if(domain.equals("pc")){
            redirectUrl = redirectDomain + "/fake-api/pay/pc/" + redirectUrl;
            tid = pcUrlTid.get(redirectUrl);
        } else {
            redirectUrl = redirectDomain + "/fake-api/pay/mobile/" + redirectUrl;
            tid = mobileUrlTid.get(redirectUrl);
        }

        if (tid.getStatus().equals(TidStatus.COMPLETE)) {
            throw new GlobalException(ErrorCode.ALREADY_COMPLETE_TID);
        }
        tid.setStatus(TidStatus.COMPLETE);

        String pgToken = UUID.randomUUID().toString().substring(0, 20);
        pgTokenMap.put(pgToken, 0);
        return pgToken;
    }

    private PayItem createPayItem(String partnerOrderId, String partnerUserId, String itemName, String totalAmount, String quantity) {
        return new PayItem(partnerOrderId, partnerUserId, quantity, totalAmount, itemName);
    }

    private String createRedirectURl(String domain) {
        String uuid = UUID.randomUUID().toString();
        return redirectDomain + "/fake-api/pay/" + domain + "/" + uuid;
    }

    private String createTid() {
        String uuid = UUID.randomUUID().toString().substring(0, 10);
        return "T" + uuid;
    }

    private void saveInfo(String tid, String pcUrl, String mobileUrl, PayItem payItem) {
        Tid tidStatus = new Tid(tid, TidStatus.PENDING);
        pcUrlTid.put(pcUrl, tidStatus);
        mobileUrlTid.put(mobileUrl, tidStatus);
        payItemMap.put(tid, payItem);
    }
}
