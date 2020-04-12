package comm.justsmile.justsite.springboot.web.controller;

import comm.justsmile.justsite.springboot.web.controller.default_ctrl.DefaultController;
import comm.justsmile.justsite.springboot.web.global.config.auth.domain.LoginUser;
import comm.justsmile.justsite.springboot.web.global.config.auth.dto.SessionUser;
import comm.justsmile.justsite.springboot.web.rest_api.crud_api.dto.PostsResponseDto;
import comm.justsmile.justsite.springboot.web.rest_api.crud_api.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(CRUDController.REF_PATH)
public class CRUDController extends DefaultController {

    protected static final String REF_PATH = "/crud";
    private final PostsService postsService;

    public CRUDController(final PostsService postsService, final HttpSession httpSession) {
        super(REF_PATH, httpSession);
        this.postsService = postsService;
    }

    @GetMapping(value = {"", "/", "/index"})
    public String index(final Model model, final @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (user != null) {
            model.addAttribute("loginUserName", user.getName());
            model.addAttribute("loginUserPicture", user.getPicture());
        }
        return resultPath("/index");
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return resultPath("/posts-save");
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(final @PathVariable Long id, final Model model){
        final PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return resultPath("/posts-update");
    }
}
