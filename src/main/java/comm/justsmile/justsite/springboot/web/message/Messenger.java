package comm.justsmile.justsite.springboot.web.message;

public interface Messenger {
    boolean send(final Payload payload);
}
