package comm.justsmile.justsite.springboot.web.global.config.auth.domain;

import comm.justsmile.justsite.springboot.web.global.domain.visitor.Role;
import comm.justsmile.justsite.springboot.web.global.domain.visitor.user.User;
import comm.justsmile.justsite.springboot.web.global.domain.visitor.Visitor;
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
    private String providerId;
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;
    private String sessionId;

    @Builder
    public OAuthAttributes(final String providerId, final Map<String, Object> attributes, final String nameAttributeKey, final String name, final String email, final String picture, final String sessionId) {
        this.providerId = providerId;
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;;
        this.sessionId = sessionId;
    }

    /**
     * 모든 Oauth 로그인 구현.
     * @param registrationId
     * @param userNameAttributeName
     * @param attributes
     * @return {@link OAuthAttributes}
     */
    public static OAuthAttributes of(final String sessionId, final String registrationId, final String userNameAttributeName, final Map<String, Object> attributes) throws OAuth2AuthenticationException {
        switch (registrationId) {
            case "google" : return ofGoogle(attributes.get("sub").toString(), sessionId, userNameAttributeName, attributes);
            case "naver" : return ofNaver(sessionId, "id", attributes);
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
    private static OAuthAttributes ofNaver(final String sessionId, final String userNameAttributeName, final Map<String, Object> attributes) {
        final Map<String, Object> response = (Map<String, Object>) attributes.get("response");
        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .sessionId(sessionId)
                .providerId(String.format("%s-%s", response.get("name"), response.get("email")))
                .build();
    }

    /**
     * GOOGLE Login 구현.
     * @param userNameAttributeName
     * @param attributes
     * @return {@link OAuthAttributes}
     */
    private static OAuthAttributes ofGoogle(final String providerId, final String sessionId, final String userNameAttributeName, final Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .providerId(providerId)
                .sessionId(sessionId)
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
    public User toEntity(final Visitor visitor, final OAuthAttributes authAttributes) {
        return new User(visitor, this.name, this.email, this.picture, authAttributes.getProviderId(), Role.USER);
    }
}
