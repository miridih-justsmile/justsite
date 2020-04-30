package comm.justsmile.justsite.springboot.web.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
@Slf4j
public class LogoutListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        log.info(String.format("생성된 Session : %s", se.getSession().getId()));

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        log.info(String.format("Session 종료 : %s", se.getSession().getId()));
    }
}