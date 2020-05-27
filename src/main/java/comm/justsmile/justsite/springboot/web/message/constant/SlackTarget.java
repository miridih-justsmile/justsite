package comm.justsmile.justsite.springboot.web.message.constant;

public enum SlackTarget {
    TEST("https://hooks.slack.com/services/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");

    private String url;

    SlackTarget(String url){
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}
