package comm.justsmile.justsite.springboot.web.api.pay.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PayRequest {
    private final String payName;
    private final Long price;
}
