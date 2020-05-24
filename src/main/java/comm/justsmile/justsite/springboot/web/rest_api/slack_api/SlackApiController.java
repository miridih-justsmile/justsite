package comm.justsmile.justsite.springboot.web.rest_api.slack_api;

import comm.justsmile.justsite.springboot.web.message.domain.Slack;
import comm.justsmile.justsite.springboot.web.message.service.SlackService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SlackApiController {

    @PostMapping("/slack/msg")
    public int sendMsg(@RequestParam("msg") final String msg){
        log.info(msg);
        if(new Slack().send(SlackService.getPayload(msg))) {
            return HttpStatus.SC_OK;
        }
        return HttpStatus.SC_BAD_REQUEST;
    }
}
