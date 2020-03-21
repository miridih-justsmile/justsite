package comm.justsmile.justsite.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SlackController {
    @GetMapping("/slack")
    public String slack() {
        return "slack";
    }
}
