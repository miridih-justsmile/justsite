package comm.justsmile.justsite.springboot.web.api.hello;

import comm.justsmile.justsite.springboot.web.api.hello.dto.HelloDto;
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
    public HelloDto getHelloDto(@RequestParam("name") final String name, @RequestParam("value") final String value) {
        return new HelloDto(name, value);
    }
}
