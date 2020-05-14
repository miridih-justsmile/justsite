package comm.justsmile.justsite.springboot.web.global.domain.visitor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    Optional<Visitor> findBySessionId(String sessionId);
    Optional<List<Visitor>> findByIpAndCreatedDateGreaterThanEqualOrderByModifiedDateDesc(String ip, LocalDateTime endDate);
}