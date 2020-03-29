package comm.justsmile.justsite.springboot.web.controller.default_ctrl;

import org.springframework.web.bind.annotation.GetMapping;

interface Controller {
    String resultPath(String path);
}
