package comm.justsmile.justsite.springboot.web.message.constant;

import comm.justsmile.justsite.springboot.web.message.domain.SlackTarget;
import comm.justsmile.justsite.springboot.web.message.repository.SlackTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.List;

@Configuration
public class SlackTargetConfig {
    private static List<SlackTarget> slackTargetList;

    @Autowired
    public SlackTargetConfig(SlackTargetRepository slackNotifyAddrRepository) {
        init(slackNotifyAddrRepository);
    }

    public static synchronized void init(SlackTargetRepository slackNotifyAddrRepository){
        slackTargetList = Collections.unmodifiableList(slackNotifyAddrRepository.findAll());
    }

    @Bean
    public static List<SlackTarget> getSlackNotifyAddrDtoList() {
        return slackTargetList;
    }
}
