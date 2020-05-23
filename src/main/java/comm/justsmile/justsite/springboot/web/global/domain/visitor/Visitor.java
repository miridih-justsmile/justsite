package comm.justsmile.justsite.springboot.web.global.domain.visitor;

import comm.justsmile.justsite.springboot.web.global.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class Visitor extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = 4002155747245838752L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idx;

    @Column(nullable = false)
    protected String sessionId;

    @Column(nullable = false)
    protected String ip;

    @Column
    protected Long loginIdx;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;

    @Builder
    public Visitor(String sessionId, String ip, Role role) {
        this.sessionId = sessionId;
        this.ip = ip;
        this.role = role;
        this.loginIdx = 0L;
    }

    /**
     * @return {@link Role}의 getKey()
     */
    public String getRoleKey() {
        return this.role.getKey();
    }

    public Visitor updateIp(final String ip) {
        this.ip = ip;
        return this;
    }

    public Visitor updateSessionId(final String sessionId) {
        this.sessionId = sessionId;
        return this;
    }

    public Visitor setLogin(final Long userIdx) {
        this.loginIdx = userIdx;
        return this;
    }

    @Override
    public String toString() {
        return "Visitor{" +
                "idx=" + idx +
                ", sessionId='" + sessionId + '\'' +
                ", ip='" + ip + '\'' +
                ", loginIdx=" + loginIdx +
                ", role=" + role +
                '}';
    }
}