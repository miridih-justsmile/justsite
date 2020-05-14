package comm.justsmile.justsite.springboot.web.global.config.auth;

import comm.justsmile.justsite.springboot.web.global.config.auth.domain.OAuthAttributes;
import comm.justsmile.justsite.springboot.web.global.config.auth.dto.SessionLoginUser;
import comm.justsmile.justsite.springboot.web.global.domain.visitor.Visitor;
import comm.justsmile.justsite.springboot.web.global.domain.visitor.VisitorRepository;
import comm.justsmile.justsite.springboot.web.global.domain.visitor.user.User;
import comm.justsmile.justsite.springboot.web.global.domain.visitor.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    /**
     * 유저정보를 가져오는 메서드. 가져와서 세션에 유저정보를 세팅해준다.
     * @param userRequest
     * @return {@link DefaultOAuth2User} 를 반환한다.
     * @throws OAuth2AuthenticationException
     */
    @Override
    public final OAuth2User loadUser(final OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        final OAuth2User oAuth2User = delegate.loadUser(userRequest);

        log.info(userRequest.getClientRegistration().toString());
        final String registrationId = userRequest.getClientRegistration().getRegistrationId();
        final String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        log.info(String.format("=============== httpSession ID : %s =================", httpSession.getId()));
        log.info(String.format("=============== httpSession IP : %s =================", httpSession.getAttribute("nowIp")));

        final OAuthAttributes attributes = OAuthAttributes.of(httpSession.getId(), registrationId, userNameAttributeName, oAuth2User.getAttributes());

        final Visitor visitor = (Visitor) httpSession.getAttribute("visitor");
        final User user = saveOrUpdate(attributes, visitor);

        log.info(attributes.toString());
        httpSession.setAttribute("user", new SessionLoginUser(user));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }

    /**
     * attributes 에 유저정보가 있으면 update, 없으면 insert 한다.
     * @param attributes {@link OAuthAttributes}
     * @return {@link VisitorRepository}의 User를 반환.
     */
    private User saveOrUpdate(final OAuthAttributes attributes, final Visitor visitor) {
        final User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(visitor, attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity(visitor, attributes));
        return userRepository.save(user);
    }
}