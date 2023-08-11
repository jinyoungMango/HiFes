package hiFes.hiFes.service.festival;


import hiFes.hiFes.domain.festival.EventNotification;
import hiFes.hiFes.domain.festival.FestivalTable;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.repository.festival.EventNotificationRepository;
import hiFes.hiFes.repository.festival.FestivalTableRepository;
import hiFes.hiFes.repository.user.NormalUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
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

    public Boolean saveEventNotification(Long normalUserId, Long programId){
        NormalUser normalUser = normalUserRepository.findById(normalUserId).orElseThrow(IllegalArgumentException::new);
        FestivalTable festivalTable = festivalTableRepository.findById(programId).orElseThrow(IllegalArgumentException::new);

        EventNotification eventNotification = new EventNotification();
        eventNotification.setNormalUser(normalUser);
        eventNotification.setFestivalTable(festivalTable);


        boolean flag = true;

        try {
            eventNotificationRepository.save(eventNotification);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            log.error(" 저장 실패");
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }
    @Transactional
    public void deleteByNormalUser_normalUserIdAndFestivalTable_programId(Long normalUserId, Long programId) {
        // EventNotification 조회
        EventNotification eventNotification = eventNotificationRepository.findByNormalUser_idAndFestivalTable_programId(normalUserId, programId)
                .orElseThrow(() -> new IllegalArgumentException("EventNotification not found with normalUserId: " + normalUserId + " and programId: " + programId));

        eventNotificationRepository.delete(eventNotification);
    }

}
