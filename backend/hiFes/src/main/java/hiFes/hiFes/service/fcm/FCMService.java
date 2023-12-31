package hiFes.hiFes.service.fcm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.auth.oauth2.GoogleCredentials;
import hiFes.hiFes.dto.fcmDto.FCMMessageDto;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

@Service
@Slf4j
public class FCMService {
    private final ObjectMapper objectMapper;
    private static final String FIREBASE_PATH = "hifes-83f33-firebase-adminsdk-fvbk6-1d84770099.json";

    @Autowired
    public FCMService(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    private String getAccessToken() throws IOException{

        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource(FIREBASE_PATH).getInputStream())
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));

        googleCredentials.refreshIfExpired();


        return googleCredentials.getAccessToken().getTokenValue();
    }

    public String makeMessage(String targetToken, String title, String body, String longitude, String latitude, String festivalId) throws JsonProcessingException {

        FCMMessageDto fcmMessage = FCMMessageDto.builder()
                .message(
                        FCMMessageDto.Message.builder()
                                .token(targetToken)
                                .data(FCMMessageDto.Data.builder()
                                        .title(title)
                                        .body(body)
                                        .longitude(longitude)
                                        .latitude(latitude)
                                        .festivalId(festivalId)
                                        .build())
                                .build()

                )
                .validateOnly(false)
                .build();

        return objectMapper.writeValueAsString(fcmMessage);

    }

    public void sendMessageTo(String targetToken, String title, String body, String longitude, String latitude ,String festivalId) throws IOException {
        String message = makeMessage(targetToken, title, body, longitude, latitude, festivalId);
        OkHttpClient client = new OkHttpClient();

        RequestBody requestBody = RequestBody.create(message, MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url("https://fcm.googleapis.com/v1/projects/hifes-83f33/messages:send")
                .post(requestBody)
                .addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+getAccessToken())
                .addHeader(HttpHeaders.CONTENT_TYPE, "application/json; UTF-8")
                .build();

        Response response = client.newCall(request).execute();

        log.info(response.body().string());
    }

}
