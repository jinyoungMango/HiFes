package hiFes.hiFes.service.festival;


import hiFes.hiFes.domain.festival.OrganizedFestival;
import hiFes.hiFes.domain.festival.ParticipatedFes;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.repository.festival.CompletedStampMissionRepository;
import hiFes.hiFes.repository.festival.OrganizedFestivalRepository;
import hiFes.hiFes.repository.festival.ParticipatedFesRepository;
import hiFes.hiFes.repository.festival.StampMissionRepository;
import hiFes.hiFes.repository.user.NormalUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@Service
public class ParticipatedFesService {

    @Autowired
    private final ParticipatedFesRepository participatedFesRepository;
    @Autowired
    private final NormalUserRepository normalUserRepository;
    @Autowired
    private final OrganizedFestivalRepository organizedFestivalRepository;
    private final CompletedStampMissionRepository completedStampMissionRepository;
    private final StampMissionRepository stampMissionRepository;


    @Autowired
    public ParticipatedFesService(NormalUserRepository normalUserRepository,
                                  ParticipatedFesRepository participatedFesRepository,
                                  OrganizedFestivalRepository organizedFestivalRepository,
                                    CompletedStampMissionRepository completedStampMissionRepository,
    StampMissionRepository stampMissionRepository ){
        this.normalUserRepository = normalUserRepository;
        this.participatedFesRepository = participatedFesRepository;
        this.organizedFestivalRepository = organizedFestivalRepository;
        this.completedStampMissionRepository = completedStampMissionRepository;
        this.stampMissionRepository = stampMissionRepository;
    }

    @Transactional
    // QR찍으면 참여
    public boolean saveParticipatedFes(Long normalUserId, Long festivalId){
        NormalUser normalUser = normalUserRepository.findById(normalUserId).orElseThrow(NoSuchElementException::new);
        OrganizedFestival organizedFestival = organizedFestivalRepository.findById(festivalId).orElseThrow(NoSuchElementException::new);

        Long countMission = completedStampMissionRepository.countCompletedStampMissionByNormalUser_idAndOrganizedFestival_FestivalId(normalUserId, festivalId);
        //완료 여부: 행사의 미션 개수 == 이 유저가 달성한 이 행사의 미션 개수.
        Long festivalMission = stampMissionRepository.countStampMissionsByFestivalId(festivalId);
        Boolean isCompleted = Objects.equals(countMission, festivalMission);
        System.out.println("완료?" + isCompleted);
        ParticipatedFes participatedFes = ParticipatedFes.builder()
                .participateTime((LocalDateTime.now()))
                .isCompleted(isCompleted)
                .countMission(countMission)
                .build();

        participatedFes.setNormalUser(normalUser);
        participatedFes.setOrganizedFestival(organizedFestival);

        boolean flag = true;

        try {
            participatedFesRepository.save(participatedFes);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            log.error("ParticipatedFes 저장 실패");
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }
    // 조회

    public List<ParticipatedFes> findNormalUserParticipatedFest(Long normalUserId){
        return participatedFesRepository.findByNormalUser_id(normalUserId);
    }



}
