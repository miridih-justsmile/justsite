package comm.justsmile.justsite.springboot.web.global.config.auth.dto;

import comm.justsmile.justsite.springboot.web.global.domain.visitor.user.User;
import lombok.Getter;

import java.io.Serializable;

/**
 * Session 에서 추출한 유저정보.
 */
@Getter
public class SessionLoginUser implements Serializable {

    private final String name;
    private final String email;
    private final String picture;
    private final Long userIdx;

    public SessionLoginUser(final User user) {
        this.userIdx = user.getIdx();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
