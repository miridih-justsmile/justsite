package comm.justsmile.justsite.springboot.web.api.pay.service;

import comm.justsmile.justsite.springboot.web.api.pay.domain.Payment;
import comm.justsmile.justsite.springboot.web.api.pay.dto.PayRequest;
import comm.justsmile.justsite.springboot.web.api.pay.dto.PayResponse;
import comm.justsmile.justsite.springboot.web.api.pay.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    public PayResponse pay(PayRequest payRequest) {
        final Payment payment = paymentRepository.save(
                Payment.builder()
                        .setPayName(payRequest.getPayName())
                        .setPrice(payRequest.getPrice())
                        .build()
        );
        return new PayResponse(payment);
    }
}
