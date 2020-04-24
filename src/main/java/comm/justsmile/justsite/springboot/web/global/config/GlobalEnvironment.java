package comm.justsmile.justsite.springboot.web.global.config;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.logging.Logger;

@Component
public class GlobalEnvironment {

    private final Environment environment;
    private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    public GlobalEnvironment(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    private void init() {
        LOGGER.info(Arrays.toString(environment.getActiveProfiles()));
        LOGGER.info(environment.getProperty("spring.mustache.prefix"));
        LOGGER.info(environment.getProperty("spring.resources.static-locations"));
    }
}
