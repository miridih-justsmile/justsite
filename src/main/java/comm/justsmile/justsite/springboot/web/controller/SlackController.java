package comm.justsmile.justsite.springboot.web.controller;

import comm.justsmile.justsite.springboot.web.controller.default_ctrl.DefaultController;
import comm.justsmile.justsite.springboot.web.message.constant.SlackTargetConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(SlackController.REF_PATH)
public class SlackController extends DefaultController {

    static final String REF_PATH = "/slack";

    public SlackController(final HttpSession httpSession) {
        super(REF_PATH, httpSession);
    }

    @GetMapping(value = {"", "/", "/index"})
    public String index(final Model model) {
        model.addAttribute("slackTargetList", SlackTargetConfig.getSlackNotifyAddrDtoList());
        return resultPath("/index");
    }
}
