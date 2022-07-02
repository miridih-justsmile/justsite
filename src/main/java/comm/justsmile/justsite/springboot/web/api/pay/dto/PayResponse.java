package comm.justsmile.justsite.springboot.web.api.pay.dto;


import comm.justsmile.justsite.springboot.web.api.pay.domain.Payment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PayResponse {
    private final Payment payment;
}
