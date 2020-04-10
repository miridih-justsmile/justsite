package comm.justsmile.justsite.springboot.web.global.config.auth;

import comm.justsmile.justsite.springboot.web.global.config.auth.domain.LoginUser;
import comm.justsmile.justsite.springboot.web.global.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import comm.justsmile.justsite.springboot.web.global.domain.user.User;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

    private final HttpSession httpSession;

    /**
     * 컨트롤러 메서드의 특정 파라미터를 지원하는지 판단.
     * @param parameter
     * @return {@link LoginUser}가 붙어있고, 파라미터 클래스 타입이 {@link SessionUser}인 경우 true
     */
    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        final boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
        final boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

        return isLoginUserAnnotation && isUserClass;
    }

    /**
     * 파라미터에 전달할 객체를 생성.
     * 세션에서 객체를 가져옴.
     * @param parameter 파라미터
     * @param mavContainer 모델&뷰
     * @param webRequest Request
     * @param binderFactory 바인더
     * @return (Object) {@link User}
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return httpSession.getAttribute("user");
    }
}
