package comm.justsmile.justsite.springboot.web.controller.default_ctrl;


public class DefaultController implements Controller {

    protected String refPath = "/";

    @Override
    public String resultPath(String path) {
        return String.format("%s%s", this.refPath, path);
    }
}
