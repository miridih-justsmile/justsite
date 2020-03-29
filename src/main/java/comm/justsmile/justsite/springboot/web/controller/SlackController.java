package comm.justsmile.justsite.springboot.web.controller;

import comm.justsmile.justsite.springboot.web.controller.default_ctrl.DefaultController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(SlackController.REF_PATH)
public class SlackController extends DefaultController {

    static final String REF_PATH = "/slack";

    public SlackController() {
        super.refPath = REF_PATH;
    }

    @GetMapping(value = {"", "/", "/index"})
    public String index() {
        return resultPath("/index");
    }

}
