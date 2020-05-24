package comm.justsmile.justsite.springboot.web.message.domain;

import com.google.gson.Gson;
import comm.justsmile.justsite.springboot.web.message.Messenger;
import comm.justsmile.justsite.springboot.web.message.MessengerException;
import comm.justsmile.justsite.springboot.web.message.Payload;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;

@Slf4j
public class Slack implements Messenger {

    @Override
    public boolean send(final Payload payload) {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost postRequest = new HttpPost(payload.getTargetUrl());
            postRequest.setHeader("Accept", "application/json");
            postRequest.setHeader("Connection", "keep-alive");
            postRequest.setHeader("Content-Type", "application/json; charset=utf-8;");

            StringEntity entity = new StringEntity(new Gson().toJson(payload), "UTF-8");
            postRequest.setEntity(entity);

            HttpResponse response = httpClient.execute(postRequest);
            if(response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                String body = handler.handleResponse(response);
                log.info(body);
            } else {
                throw new MessengerException("슬랙 메시지 발송 실패");
            }
            return Boolean.TRUE;
        } catch (MessengerException e) {
            log.error(e.getMessage());
        } catch (Exception e) {
            log.error("알 수 없는 에러 발생", e);
        }
        return Boolean.FALSE;
    }
}
