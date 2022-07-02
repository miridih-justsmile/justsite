package comm.justsmile.justsite.springboot.web.controller;

import comm.justsmile.justsite.springboot.web.controller.common.DefaultController;
import comm.justsmile.justsite.springboot.web.global.config.auth.domain.LoginUser;
import comm.justsmile.justsite.springboot.web.global.config.auth.dto.SessionLoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(IndexController.REF_PATH)
public class IndexController extends DefaultController {
    static final String REF_PATH = "/";

    public IndexController(final HttpSession session) {
        super(REF_PATH, session);
    }

    @GetMapping(value = {"", "/", "/index"})
    public String index(final Model model, @LoginUser final SessionLoginUser loginUser) {

        return super.resultPath("/index");
    }

}
