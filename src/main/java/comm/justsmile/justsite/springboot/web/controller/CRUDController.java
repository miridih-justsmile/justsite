package comm.justsmile.justsite.springboot.web.controller;

import comm.justsmile.justsite.springboot.web.controller.common.DefaultController;
import comm.justsmile.justsite.springboot.web.global.config.auth.domain.LoginUser;
import comm.justsmile.justsite.springboot.web.global.config.auth.dto.SessionLoginUser;
import comm.justsmile.justsite.springboot.web.api.crud.dto.PostsResponseDto;
import comm.justsmile.justsite.springboot.web.api.crud.service.PostsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(CRUDController.REF_PATH)
public class CRUDController extends DefaultController {

    static final String REF_PATH = "/crud";
    private final PostsService postsService;

    public CRUDController(final PostsService postsService, final HttpSession httpSession) {
        super(REF_PATH, httpSession);
        this.postsService = postsService;
    }

    @GetMapping(value = {"", "/", "/index"})
    public String index(final Model model, @LoginUser final SessionLoginUser loginUser) {
        model.addAttribute("posts", postsService.findAllDesc());
        if (loginUser != null) {
            model.addAttribute("loginUserName", loginUser.getName());
            model.addAttribute("loginUserPicture", loginUser.getPicture());
        }
        return resultPath("/index");
    }

    @GetMapping("/posts/save")
    public String postsSave(final Model model, @LoginUser final SessionLoginUser loginUser) {
        if(loginUser != null) {
            model.addAttribute("loginUserName", loginUser.getName());
        }
        return resultPath("/posts/save");
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable final Long id, final Model model){
        final PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);
        return resultPath("/posts/update");
    }
}
