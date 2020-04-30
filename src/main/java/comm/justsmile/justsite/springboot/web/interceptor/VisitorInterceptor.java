package comm.justsmile.justsite.springboot.web.interceptor;

import comm.justsmile.justsite.springboot.web.global.domain.visitor.VisitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@Component
public class VisitorInterceptor extends HandlerInterceptorAdapter {

    public final VisitorService visitorService;
    public final HttpSession httpSession;

    @Autowired
    public VisitorInterceptor(VisitorService visitorService, HttpSession httpSession) {
        this.visitorService = visitorService;
        this.httpSession = httpSession;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        httpSession.setAttribute("visitor", visitorService.saveOrUpdate());
        return true;
    }
}
