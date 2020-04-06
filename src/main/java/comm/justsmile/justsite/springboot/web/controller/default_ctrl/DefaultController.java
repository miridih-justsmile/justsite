package comm.justsmile.justsite.springboot.web.controller.default_ctrl;

import comm.justsmile.justsite.springboot.web.global.config.auth.dto.SessionUser;

import javax.servlet.http.HttpSession;

public class DefaultController implements Controller {

    protected String refPath = "/";
    protected HttpSession httpSession;

    @Override
    public String resultPath(String path) {
        String resultPath = String.format("%s%s", this.refPath, path);
        System.out.println("들어온 주소 : " + resultPath);
        return resultPath;
    }
}
