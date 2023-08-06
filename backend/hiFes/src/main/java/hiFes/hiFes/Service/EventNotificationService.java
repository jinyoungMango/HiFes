package hiFes.hiFes.Service;


import hiFes.hiFes.domain.EventNotification;
import hiFes.hiFes.domain.FestivalTable;
import hiFes.hiFes.domain.NormalUser;
import hiFes.hiFes.dto.AddEventNotificationRequest;
import hiFes.hiFes.repository.EventNotificationRepository;
import hiFes.hiFes.repository.FestivalTableRepository;
import hiFes.hiFes.repository.NormalUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    public void saveEventNotification(Long normalUserId, Long programId){
        NormalUser normalUser = normalUserRepository.findById(normalUserId).orElseThrow(NoSuchElementException::new);
        FestivalTable festivalTable = festivalTableRepository.findById(programId).orElseThrow(NoSuchElementException::new);

        EventNotification eventNotification = new EventNotification();
        eventNotification.setNormalUser(normalUser);
        eventNotification.setFestivalTable(festivalTable);

        eventNotificationRepository.save(eventNotification);
    }
    @Transactional
    public void deleteByNormalUser_normalUserIdAndFestivalTable_programId(Long normalUserId, Long programId) {
        // EventNotification 조회
        Optional<EventNotification> eventNotificationOptional = eventNotificationRepository.findByNormalUser_NormalUserIdAndFestivalTable_programId(normalUserId, programId);

        if (!eventNotificationOptional.isPresent()) {
            throw new IllegalArgumentException("EventNotification not found with normalUserId: " + normalUserId + " and programId: " + programId);
        }

        eventNotificationRepository.delete(eventNotificationOptional.get());
    }
}
