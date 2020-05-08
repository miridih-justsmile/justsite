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

    @Id
    @GeneratedValue
    protected Long id;

    @Column(nullable = false)
    protected String sessionId;

    @Column(nullable = false)
    protected String ip;

    @Column
    protected boolean isLogin = false;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;

    @Builder
    public Visitor(String sessionId, String ip, Role role) {
        this.sessionId = sessionId;
        this.ip = ip;
        this.role = role;
    }

    /**
     * @return {@link Role}의 getKey()
     */
    public String getRoleKey() {
        return this.role.getKey();
    }

    public Visitor update(final String sessionId, final String ip) {
        this.sessionId = sessionId;
        this.ip = ip;
        return this;
    }
}