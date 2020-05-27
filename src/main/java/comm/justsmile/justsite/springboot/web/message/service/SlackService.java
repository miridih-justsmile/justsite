package comm.justsmile.justsite.springboot.web.message.service;

import comm.justsmile.justsite.springboot.web.message.Payload;
import comm.justsmile.justsite.springboot.web.message.constant.SlackTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlackService {

    private final SlackTarget slackTarget;

    @Autowired
    public SlackService(SlackTarget slackTarget) {
        this.slackTarget = slackTarget;
    }

    public Payload getPayload(String text){
       return new SlackPayload(slackTarget.getTest(), text);
    }

}
