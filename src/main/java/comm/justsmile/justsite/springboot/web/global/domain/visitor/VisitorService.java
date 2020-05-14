package comm.justsmile.justsite.springboot.web.global.domain.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;
    private final HttpSession httpSession;

    @Autowired
    public VisitorService(final VisitorRepository visitorRepository, final HttpSession httpSession) {
        this.visitorRepository = visitorRepository;
        this.httpSession = httpSession;
    }

    @Transactional
    public Visitor saveOrUpdate() {
        final String ip = httpSession.getAttribute("nowIp").toString();
        final Visitor visitor = visitorRepository.findBySessionId(httpSession.getId())
                .map(entity -> entity.updateIp(ip))
                .orElse(visitorRepository
                        .findByIpAndCreatedDateGreaterThanEqualOrderByModifiedDateDesc(ip, LocalDateTime.now().minusSeconds(10))
                        .map(entity -> entity.get(0).updateSessionId(httpSession.getId()))
                        .orElse(Visitor.builder().ip(httpSession.getAttribute("nowIp").toString())
                                .sessionId(httpSession.getId())
                                .role(Role.GUEST)
                                .build()
                        )
                );
        return visitorRepository.save(visitor);
    }

    public Visitor update(final Visitor visitor) {
        System.out.println(visitor.toString());
        return visitorRepository.save(visitor);
    }
}
