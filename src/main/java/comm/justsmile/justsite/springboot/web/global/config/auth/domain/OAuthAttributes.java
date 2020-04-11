package comm.justsmile.justsite.springboot.web.global.config.auth.domain;

import comm.justsmile.justsite.springboot.web.global.domain.user.Role;
import comm.justsmile.justsite.springboot.web.global.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

import java.util.Map;

@Getter
@ToString
public class OAuthAttributes {
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthAttributes.class);
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
     * @param registrationId
     * @param userNameAttributeName
     * @param attributes
     * @return {@link OAuthAttributes}
     */
    public static OAuthAttributes of(final String registrationId, final String userNameAttributeName, final Map<String, Object> attributes) throws OAuth2AuthenticationException {
        switch (registrationId) {
            case "google" : return ofGoogle(userNameAttributeName, attributes);
            case "naver" : return ofNaver("id", attributes);
            default :
                throw new OAuth2AuthenticationException(new OAuth2Error(String.valueOf(HttpStatus.UNAUTHORIZED.value())), "찾을 수 없는 로그인 방식");
        }
    }

    /**
     * Naver Login 구현
     * @param userNameAttributeName
     * @param attributes
     * @return {@link OAuthAttributes}
     */
    private static OAuthAttributes ofNaver(final String userNameAttributeName, final Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
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
