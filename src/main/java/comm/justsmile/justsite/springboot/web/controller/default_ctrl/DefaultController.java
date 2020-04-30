package comm.justsmile.justsite.springboot.web.controller.default_ctrl;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpSession;

@Slf4j
public abstract class DefaultController implements Controller {

    /**
     * refPath : reference 가 되는 path.
     */
    private final String refPath;
    private final HttpSession httpSession;

    public DefaultController(final String refPath, final HttpSession httpSession) {
        this.refPath = refPath;
        this.httpSession = httpSession;
    }

    @Override
    public final String resultPath(final String path) {
        String resultPath = String.format("%s%s", this.refPath, path);
        log.info("반환되는 주소 : " + resultPath);
        return resultPath;
    }
}
