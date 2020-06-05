package comm.justsmile.justsite.springboot.web.message.service;

import com.google.gson.Gson;
import comm.justsmile.justsite.springboot.web.message.MessengerException;
import comm.justsmile.justsite.springboot.web.message.constant.SlackTargetConfig;
import comm.justsmile.justsite.springboot.web.message.domain.Payload;
import comm.justsmile.justsite.springboot.web.message.domain.SlackTarget;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class SlackService implements MessengerService {

    private SlackTargetConfig slackTargetConfig;

    @Autowired
    public SlackService(SlackTargetConfig slackTargetConfig) {
        this.slackTargetConfig = slackTargetConfig;
    }

    @Override
    public boolean send(final Payload payload) {
        try(final CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {

            final HttpPost postRequest = new HttpPost(payload.getTargetUrl());
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Connection", "keep-alive");
            postRequest.setHeader("Content-Type", "application/json; charset=utf-8");

            final StringEntity entity = new StringEntity(new Gson().toJson(payload), "UTF-8");
            entity.setContentType("application/json");
            postRequest.setEntity(entity);

            try(final CloseableHttpResponse response = httpClient.execute(postRequest)) {
                if(response.getStatusLine().getStatusCode() == 200) {
                    ResponseHandler<String> handler = new BasicResponseHandler();
                    String body = handler.handleResponse(response);
                    log.info(body);
                } else {
                    log.error(response.toString());
                    throw new MessengerException("슬랙 메시지 발송 실패");
                }
            }
            return Boolean.TRUE;
        } catch (MessengerException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("알 수 없는 에러 발생", e);
        }
        return Boolean.FALSE;
    }

    public SlackTarget findSlackTarget(final String targetName) throws MessengerException {
        final Optional<SlackTarget> optionalSlackNotifyAddrDto = slackTargetConfig
                .getSlackNotifyAddrDtoList()
                .stream()
                .filter(dto -> dto.getName().equals(targetName))
                .findFirst();

        return optionalSlackNotifyAddrDto.orElseThrow(() -> new MessengerException("타겟 이름이 없음"));
    }
}
