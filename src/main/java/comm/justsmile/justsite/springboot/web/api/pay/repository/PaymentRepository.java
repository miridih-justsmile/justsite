package comm.justsmile.justsite.springboot.web.api.pay.repository;

import comm.justsmile.justsite.springboot.web.api.pay.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}