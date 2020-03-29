package comm.justsmile.justsite.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CRUDController.REF_PATH)
public class CRUDController extends DefaultController{

    static final String REF_PATH = "/crud";

    public CRUDController() {
        super.refPath = REF_PATH;
    }
}
