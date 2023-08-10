package hiFes.hiFes.controller;
import hiFes.hiFes.service.EventNotificationService;
import hiFes.hiFes.domain.EventNotification;
import hiFes.hiFes.repository.EventNotificationRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="회원-이벤트 일정 알림 등록, 삭제 컨트롤러")
public class EventNotificationApiController {

    @Autowired
    private EventNotificationService eventNotificationService;
    @Autowired
    private EventNotificationRepository eventNotificationRepository;

    @Operation(summary = "특정 회원이 행사 일정 알림 등록")
    @PostMapping("api/{normalUserId}/regist-eventNotifications/{programId}")
    public EventNotification saveEventNotification(@PathVariable Long normalUserId, @PathVariable Long programId) {
        return eventNotificationService.saveEventNotification(normalUserId, programId);
    }

    @Operation(summary = "특정 회원이 행사 일정 알림 삭제")
    @DeleteMapping("api/{normalUserId}/delete-eventNotifications/{programId}")
    public ResponseEntity<Void> deleteEventNotification(@PathVariable Long normalUserId, @PathVariable Long programId){
        eventNotificationRepository.deleteByNormalUser_idAndFestivalTable_programId(normalUserId,programId);
        return ResponseEntity.ok()
                .build();
    }
}
