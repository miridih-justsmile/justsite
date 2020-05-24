package comm.justsmile.justsite.springboot.web.message.service;

import comm.justsmile.justsite.springboot.web.message.Payload;
import comm.justsmile.justsite.springboot.web.message.constant.SlackTarget;

public class SlackService {
    public static Payload getPayload(String text){
       return new SlackPayload(SlackTarget.TEST.getUrl(), text);
    }
}
