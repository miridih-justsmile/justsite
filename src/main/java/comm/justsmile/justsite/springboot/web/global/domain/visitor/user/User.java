package comm.justsmile.justsite.springboot.web.global.domain.visitor.user;

import comm.justsmile.justsite.springboot.web.global.domain.BaseTimeEntity;
import comm.justsmile.justsite.springboot.web.global.domain.visitor.Role;
import comm.justsmile.justsite.springboot.web.global.domain.visitor.Visitor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User 정보 Entity.
 */

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idx;

    @Column
    private String providerId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Column
    private Long lastVisit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected Role role;

    /**
     * @return {@link Role}의 getKey()
     */
    public String getRoleKey() {
        return this.role.getKey();
    }

    /**
     * @param visitor {@link Visitor}
     * @param name 이름
     * @param email 이메일
     * @param picture 사진
     * @param providerId 프로바이더 ID
     * @param role 권한
     */
    public User(final Visitor visitor, final String name, final String email, final String picture, final String providerId, final Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.lastVisit = visitor.getIdx();
        this.providerId = providerId;
        this.role = role;
    }

    public User update(final Visitor visitor, final String name, final String picture) {
        this.name = name;
        this.picture = picture;
        this.lastVisit = visitor.getIdx();
        return this;
    }
}
