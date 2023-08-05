package hiFes.hiFes.dto;

import hiFes.hiFes.domain.EventNotification;
import hiFes.hiFes.domain.FestivalTable;
import hiFes.hiFes.domain.NormalUser;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddEventNotificationRequest {

    private Long eventNotificationId;
    private Long normalUserId;
    private Long programId;


}
