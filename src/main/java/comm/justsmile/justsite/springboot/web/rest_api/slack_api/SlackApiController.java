package comm.justsmile.justsite.springboot.web.rest_api.slack_api;

import comm.justsmile.justsite.springboot.web.message.MessengerException;
import comm.justsmile.justsite.springboot.web.message.domain.SlackTarget;
import comm.justsmile.justsite.springboot.web.message.service.SlackPayload;
import comm.justsmile.justsite.springboot.web.message.service.SlackService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SlackApiController {

    private final SlackService slackService;

    @Autowired
    public SlackApiController(final SlackService slackService) {
        this.slackService = slackService;
    }

    @PostMapping("/slack/msg")
    public int sendMsg(@RequestParam("msg") final String msg, @RequestParam("target")final String target) throws MessengerException {
        log.info(msg);
        final SlackTarget notifyTarget = slackService.findSlackTarget(target);
        if(slackService.send(new SlackPayload(notifyTarget.getTarget(), msg))) {
            return HttpStatus.SC_OK;
        }
        return HttpStatus.SC_BAD_REQUEST;
    }

}
