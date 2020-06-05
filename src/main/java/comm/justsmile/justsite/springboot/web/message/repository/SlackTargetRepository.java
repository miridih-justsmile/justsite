package comm.justsmile.justsite.springboot.web.message.repository;

import comm.justsmile.justsite.springboot.web.message.domain.SlackTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlackTargetRepository extends JpaRepository<SlackTarget, Integer> {

}
