package hiFes.hiFes.service.festival;

import hiFes.hiFes.domain.festival.CompletedStampMission;
import hiFes.hiFes.domain.festival.ParticipatedFes;
import hiFes.hiFes.domain.festival.StampMission;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.festival.CompletedStampMissionResponse;
import hiFes.hiFes.repository.festival.CompletedStampMissionRepository;
import hiFes.hiFes.repository.festival.ParticipatedFesRepository;
import hiFes.hiFes.repository.festival.StampMissionRepository;
import hiFes.hiFes.repository.user.NormalUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompletedStampMissionService {

    @Autowired
    private final CompletedStampMissionRepository completedStampMissionRepository;
    private final NormalUserRepository normalUserRepository;
    private final StampMissionRepository stampMissionRepository;
    private final ParticipatedFesRepository participatedFesRepository;


    @Autowired
    public CompletedStampMissionService(NormalUserRepository normalUserRepository,
                                        CompletedStampMissionRepository completedStampMissionRepository,
                                        StampMissionRepository stampMissionRepository,
                                        ParticipatedFesRepository participatedFesRepository) {
        this.normalUserRepository = normalUserRepository;
        this.completedStampMissionRepository = completedStampMissionRepository;
        this.stampMissionRepository = stampMissionRepository;
        this.participatedFesRepository = participatedFesRepository;
    }

    @Transactional
    public Boolean saveCompletedStampMission(Long normalUserId, Long missionId){
        NormalUser normalUser = normalUserRepository.findById(normalUserId).orElseThrow(NoSuchElementException::new);
        StampMission stampMission = stampMissionRepository.findById(missionId).orElseThrow(NoSuchElementException::new);
        boolean flag = true;

        if(completedStampMissionRepository.existsByNormalUser_IdAndStampMission_MissionId(normalUserId,missionId)){
            flag = false;
            return flag;
        }

        CompletedStampMission completedStampMission = new CompletedStampMission();
        completedStampMission.setNormalUser(normalUser);
        completedStampMission.setStampMission(stampMission);
        Long festivalId = stampMission.getOrganizedFestival().getFestivalId();

        ParticipatedFes participatedFes = participatedFesRepository.findByNormalUser_IdAndOrganizedFestival_FestivalId(normalUserId,festivalId);

        try {
            completedStampMissionRepository.save(completedStampMission);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            log.error(" 저장 실패");
            e.printStackTrace();
            flag = false;
        }
        //스탬프 찍으면서 완료 여부 계속 업데이트.
        Long countMission = completedStampMissionRepository.countCompletedStampMissionByNormalUser_idAndOrganizedFestival_FestivalId(normalUserId, festivalId);
        Long festivalMission = stampMissionRepository.countStampMissionsByFestivalId(festivalId);
        Boolean isCompleted = Objects.equals(countMission, festivalMission);
        participatedFes.setIsCompleted(isCompleted);
        participatedFesRepository.save(participatedFes);

        return flag;

    }

    public  List<Long> getCompletedMissionIdsByUserIdAndFestivalId(Long userId, Long festivalId) {
        return completedStampMissionRepository.findCompletedStampMission_idByNormalUser_idAndOrganizedFestival_FestivalId(userId, festivalId );
    }

}
