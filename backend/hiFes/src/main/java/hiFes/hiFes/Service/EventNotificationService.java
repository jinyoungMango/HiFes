package hiFes.hiFes.Service;


import hiFes.hiFes.domain.EventNotification;
import hiFes.hiFes.domain.FestivalTable;
import hiFes.hiFes.domain.NormalUser;
import hiFes.hiFes.repository.EventNotificationRepository;
import hiFes.hiFes.repository.FestivalTableRepository;
import hiFes.hiFes.repository.NormalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class EventNotificationService {
    @Autowired
    private final EventNotificationRepository eventNotificationRepository;
    private final NormalUserRepository normalUserRepository;
    private final FestivalTableRepository festivalTableRepository;

    @Autowired
    public EventNotificationService(NormalUserRepository normalUserRepository,
                               FestivalTableRepository festivalTableRepository,
                               EventNotificationRepository eventNotificationRepository) {
        this.normalUserRepository = normalUserRepository;
        this.festivalTableRepository = festivalTableRepository;
        this.eventNotificationRepository = eventNotificationRepository;
    }

    public EventNotification saveEventNotification(Long normalUserId, Long programId){
        NormalUser normalUser = normalUserRepository.findById(normalUserId).orElseThrow(IllegalArgumentException::new);
        FestivalTable festivalTable = festivalTableRepository.findById(programId).orElseThrow(IllegalArgumentException::new);

        EventNotification eventNotification = new EventNotification();
        eventNotification.setNormalUser(normalUser);
        eventNotification.setFestivalTable(festivalTable);

        return eventNotificationRepository.save(eventNotification);
    }
    @Transactional
    public void deleteByNormalUser_normalUserIdAndFestivalTable_programId(Long normalUserId, Long programId) {
        // EventNotification 조회
        EventNotification eventNotification = eventNotificationRepository.findByNormalUser_NormalUserIdAndFestivalTable_programId(normalUserId, programId)
                .orElseThrow(() -> new IllegalArgumentException("EventNotification not found with normalUserId: " + normalUserId + " and programId: " + programId));

        eventNotificationRepository.delete(eventNotification);
    }

}
