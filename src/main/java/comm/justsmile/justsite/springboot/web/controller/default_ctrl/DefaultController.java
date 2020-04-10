package comm.justsmile.justsite.springboot.web.controller.default_ctrl;

import javax.servlet.http.HttpSession;

public abstract class DefaultController implements Controller {

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
        System.out.println("들어온 주소 : " + resultPath);
        return resultPath;
    }
}
