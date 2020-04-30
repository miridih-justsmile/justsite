package comm.justsmile.justsite.springboot.web.global.domain.visitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;

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
        return visitorRepository.findBySessionId(httpSession.getId()).map(
                entity -> entity.update(httpSession.getId(), httpSession.getAttribute("nowIp").toString())
        ).orElse(Visitor.builder()
                .ip(httpSession.getAttribute("nowIp").toString())
                .sessionId(httpSession.getId())
                .role(Role.GUEST)
                .build()
        );
    }
}
