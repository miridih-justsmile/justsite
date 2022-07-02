package comm.justsmile.justsite.springboot.web.api.config;

import comm.justsmile.justsite.springboot.web.message.constant.SlackTargetConfig;
import comm.justsmile.justsite.springboot.web.message.repository.SlackTargetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ConfigApiController {

    private SlackTargetRepository slackNotifyAddrRepository;

    @Autowired
    public ConfigApiController(SlackTargetRepository slackNotifyAddrRepository) {
        this.slackNotifyAddrRepository = slackNotifyAddrRepository;
    }

    @GetMapping("/config/init/{cmd}")
    public void findById(@PathVariable final String cmd) {
        if("slack".equals(cmd)) {
            SlackTargetConfig.init(slackNotifyAddrRepository);
        }
    }
}
