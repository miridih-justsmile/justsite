package comm.justsmile.justsite.springboot.web.controller.default_ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

public abstract class DefaultController implements Controller {

    protected static Logger LOGGER = LoggerFactory.getLogger(DefaultController.class);

    /**
     * refPath : reference 가 되는 path.
     */
    protected String refPath = "/";
    protected HttpSession httpSession;

    public DefaultController(String refPath, HttpSession httpSession) {
        this.refPath = refPath;
        this.httpSession = httpSession;
    }

    @Override
    public String resultPath(String path) {
        String resultPath = String.format("%s%s", this.refPath, path);
        LOGGER.info("반환되는 주소 : " + resultPath);
        return resultPath;
    }
}
