package comm.justsmile.justsite.springboot.web.controller.default_ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

public abstract class DefaultController implements Controller {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
        LOGGER.info("반환되는 주소 : " + resultPath);
        return resultPath;
    }
}
