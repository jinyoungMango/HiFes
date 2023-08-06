package hiFes.hiFes.Controller;

import hiFes.hiFes.domain.EventNotification;
import hiFes.hiFes.domain.FestivalTable;
import hiFes.hiFes.domain.NormalUser;
import hiFes.hiFes.repository.EventNotificationRepository;
import hiFes.hiFes.repository.FestivalTableRepository;
import hiFes.hiFes.repository.NormalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
public class EventNotificationApiController {

    private final NormalUserRepository normalUserRepository;
    private final FestivalTableRepository festivalTableRepository;
    private final EventNotificationRepository eventNotificationRepository;

    @Autowired
    public EventNotificationApiController(NormalUserRepository normalUserRepository, FestivalTableRepository festivalTableRepository, EventNotificationRepository eventNotificationRepository) {
        this.normalUserRepository = normalUserRepository;
        this.festivalTableRepository = festivalTableRepository;
        this.eventNotificationRepository = eventNotificationRepository;
    }

    @PostMapping("api/{normalUserId}/regist-eventNotifications/{programId}")
    public EventNotification saveEventNotification(@PathVariable Long normalUserId, @PathVariable Long programId) {
        NormalUser normalUser = normalUserRepository.findById(normalUserId).orElseThrow(NoSuchElementException::new);
        FestivalTable festivalTable = festivalTableRepository.findById(programId).orElseThrow(NoSuchElementException::new);

        EventNotification eventNotification = new EventNotification();
        eventNotification.setNormalUser(normalUser);
        eventNotification.setFestivalTable(festivalTable);

        return eventNotificationRepository.save(eventNotification);
    }

    @DeleteMapping("api/{normalUserId}/delete-eventNotifications/{programId}")
    public ResponseEntity<Void> deleteEventNotification(@PathVariable Long normalUserId, @PathVariable Long programId){
        eventNotificationRepository.deleteByNormalUser_normalUserIdAndFestivalTable_programId(normalUserId,programId);
        return ResponseEntity.ok()
                .build();
    }
}
