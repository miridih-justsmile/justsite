package comm.justsmile.justsite.springboot.web.message.service;

import comm.justsmile.justsite.springboot.web.message.domain.Payload;

public class SlackPayload implements Payload {

    private final String text;
    private final String targetUrl;

    public SlackPayload(final String targetUrl, final String text) {
        this.targetUrl = targetUrl;
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public String getTargetUrl() {
        return this.targetUrl;
    }
}
