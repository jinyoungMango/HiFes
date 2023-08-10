package hiFes.hiFes.repository.festival;

import hiFes.hiFes.domain.festival.EventNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface EventNotificationRepository extends JpaRepository<EventNotification, Long> {
    Optional<EventNotification> findByNormalUser_idAndFestivalTable_programId(Long id, Long programId);


    @Transactional
    void deleteByNormalUser_idAndFestivalTable_programId(Long id, Long programId);
}
