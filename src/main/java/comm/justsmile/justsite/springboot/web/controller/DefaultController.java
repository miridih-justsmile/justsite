package comm.justsmile.justsite.springboot.web.controller;


public class DefaultController implements Controller {

    protected String refPath = "/";

    @Override
    public String index() {
        return resultPath("/index");
    }

    @Override
    public String resultPath(String path) {
        return String.format("%s/index", this.refPath);
    }
}
