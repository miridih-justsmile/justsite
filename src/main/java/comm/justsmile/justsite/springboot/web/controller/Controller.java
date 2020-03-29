package comm.justsmile.justsite.springboot.web.controller;

import org.springframework.web.bind.annotation.GetMapping;

interface Controller {

    @GetMapping(value = {"", "/", "/index"})
    String index();

    String resultPath(String path);
}
