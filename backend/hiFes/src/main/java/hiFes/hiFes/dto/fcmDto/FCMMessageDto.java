package hiFes.hiFes.dto.fcmDto;

import com.google.firebase.messaging.Notification;
import lombok.*;

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
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class Notification{
        private String title;
        private String body;
    }




}
