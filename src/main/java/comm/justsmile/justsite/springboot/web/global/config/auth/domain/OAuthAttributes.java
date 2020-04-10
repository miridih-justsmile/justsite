package comm.justsmile.justsite.springboot.web.global.config.auth.domain;

import comm.justsmile.justsite.springboot.web.global.domain.user.Role;
import comm.justsmile.justsite.springboot.web.global.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(final Map<String, Object> attributes, final String nameAttributeKey, final String name, final String email, final String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    /**
     * 모든 Oauth 로그인 구현.
     * todo.registrationId는 현재 구글로그인에서는 사용하지않고 있으나, 다른 로그인에서는 사용 할 수도 있음.
     * @param registrationId
     * @param userNameAttributeName
     * @param attributes
     * @return {@link OAuthAttributes}
     */
    public static OAuthAttributes of(final String registrationId, final String userNameAttributeName, final Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }

    /**
     * GOOGLE Login 구현.
     * @param userNameAttributeName
     * @param attributes
     * @return {@link OAuthAttributes}
     */
    private static OAuthAttributes ofGoogle(final String userNameAttributeName, final Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    /**
     * entity 데이터 세팅.
     * @return {@link User}
     */
    public User toEntity() {
        return User.builder()
                .name(this.name)
                .email(this.email)
                .picture(this.picture)
                .role(Role.USER)
                .build();
    }
}
