package comm.justsmile.justsite.springboot.web.api.pay.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder(setterPrefix = "set")
public class Payment {
    private Long idx;
    private String payName;
    private Long price;
}