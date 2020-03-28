package comm.justsmile.justsite.springboot.web.rest_api.hello_api;

import comm.justsmile.justsite.springboot.web.rest_api.hello_api.dto.HelloDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloDto getHelloDto(@RequestParam("name") String name, @RequestParam("value") String value) {
        return new HelloDto(name, value);
    }
}
