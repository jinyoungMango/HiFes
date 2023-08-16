package hiFes.hiFes.dto.fcmDto;

import com.google.firebase.messaging.Notification;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@Getter
public class FCMMessageDto {
    private boolean validateOnly;
    private Message message;

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Message{
        private Notification notification;
        private String token;
        private Data data;
    }

//    @Builder
//    @Getter
//    @AllArgsConstructor
//    public static class Notification{
//        private String title;
//        private String body;
//    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Data{
        private String title;
        private String body;
        private String longitude;
        private String latitude;
        private String festivalId;
    }




}
