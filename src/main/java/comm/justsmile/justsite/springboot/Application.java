package comm.justsmile.justsite.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // Auto Compile 여부.
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(Application.class, args);
    }
}
