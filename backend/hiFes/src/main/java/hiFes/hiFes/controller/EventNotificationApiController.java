package hiFes.hiFes.controller;

import hiFes.hiFes.service.EventNotificationService;
import hiFes.hiFes.domain.EventNotification;
import hiFes.hiFes.repository.EventNotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EventNotificationApiController {

    @Autowired
    private EventNotificationService eventNotificationService;
    @Autowired
    private EventNotificationRepository eventNotificationRepository;

    @PostMapping("api/{normalUserId}/regist-eventNotifications/{programId}")
    public EventNotification saveEventNotification(@PathVariable Long normalUserId, @PathVariable Long programId) {
        return eventNotificationService.saveEventNotification(normalUserId, programId);
    }

    @DeleteMapping("api/{normalUserId}/delete-eventNotifications/{programId}")
    public ResponseEntity<Void> deleteEventNotification(@PathVariable Long normalUserId, @PathVariable Long programId){
        eventNotificationRepository.deleteByNormalUser_idAndFestivalTable_programId(normalUserId,programId);
        return ResponseEntity.ok()
                .build();
    }
}
