package comm.justsmile.justsite.springboot.web.global.domain.visitor.user;

import comm.justsmile.justsite.springboot.web.global.domain.visitor.Role;
import comm.justsmile.justsite.springboot.web.global.domain.visitor.Visitor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * User 정보 Entity.
 */

@Getter
@NoArgsConstructor
@Entity
public class User extends Visitor {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    /**
     *
     * @param visitor {@link Visitor}
     * @param name 이름
     * @param email 이메일
     * @param picture 사진
     * @param role 권한
     */
    public User(final Visitor visitor, final String name, final String email, final String picture, final Role role) {
        this.sessionId = visitor.getSessionId();
        this.ip = visitor.getIp();
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.isLogin = true;
    }

    public User update(final Visitor visitor, final String name, final String picture) {
        this.sessionId = visitor.getSessionId();
        this.ip = visitor.getIp();
        this.name = name;
        this.picture = picture;
        return this;
    }
}
