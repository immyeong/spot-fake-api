package spot.fakeApi.global.healthcheck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthCheck {

    @GetMapping("/health-check")
    public String healthcheck() {
        log.info("health-check/ok");
        return "ok!";
    }
}
