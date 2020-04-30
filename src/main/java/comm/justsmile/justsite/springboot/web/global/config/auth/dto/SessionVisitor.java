package comm.justsmile.justsite.springboot.web.global.config.auth.dto;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionVisitor implements Serializable {
    protected String userIp;
    protected String sessionId;

    public SessionVisitor(String userIp, String sessionId) {
        this.userIp = userIp;
        this.sessionId = sessionId;
    }
}
