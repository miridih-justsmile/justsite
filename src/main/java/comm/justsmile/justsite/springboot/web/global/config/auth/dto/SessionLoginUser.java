package comm.justsmile.justsite.springboot.web.global.config.auth.dto;

import comm.justsmile.justsite.springboot.web.global.domain.visitor.user.User;
import lombok.Getter;

/**
 * Session 에서 추출한 유저정보.
 */
@Getter
public class SessionLoginUser extends SessionVisitor {
    private final String name;
    private final String email;
    private final String picture;

    public SessionLoginUser(final User user) {
        super(user.getIp(), user.getIp());
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
