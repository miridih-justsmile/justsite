package comm.justsmile.justsite.springboot.web.message.service;

import comm.justsmile.justsite.springboot.web.message.Payload;

class SlackPayload implements Payload {

    private final String text;
    private final String targetUrl;

    SlackPayload(final String targetUrl, final String text) {
        this.targetUrl = targetUrl;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getTargetUrl() {
        return targetUrl;
    }
}
