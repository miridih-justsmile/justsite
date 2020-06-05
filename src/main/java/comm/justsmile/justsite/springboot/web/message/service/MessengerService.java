package comm.justsmile.justsite.springboot.web.message.service;

import comm.justsmile.justsite.springboot.web.message.domain.Payload;

interface MessengerService {
    boolean send(final Payload payload);
}
