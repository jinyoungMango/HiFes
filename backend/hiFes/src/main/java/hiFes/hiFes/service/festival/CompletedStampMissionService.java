package hiFes.hiFes.service.festival;

import hiFes.hiFes.domain.festival.CompletedStampMission;
import hiFes.hiFes.domain.festival.StampMission;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.festival.CompletedStampMissionResponse;
import hiFes.hiFes.repository.festival.CompletedStampMissionRepository;
import hiFes.hiFes.repository.festival.StampMissionRepository;
import hiFes.hiFes.repository.user.NormalUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CompletedStampMissionService {

    @Autowired
    private final CompletedStampMissionRepository completedStampMissionRepository;
    private final NormalUserRepository normalUserRepository;
    private final StampMissionRepository stampMissionRepository;


    @Autowired
    public CompletedStampMissionService(NormalUserRepository normalUserRepository,
                                        CompletedStampMissionRepository completedStampMissionRepository,
                                        StampMissionRepository stampMissionRepository) {
        this.normalUserRepository = normalUserRepository;
        this.completedStampMissionRepository = completedStampMissionRepository;
        this.stampMissionRepository = stampMissionRepository;
    }

    @Transactional
    public Boolean saveCompletedStampMission(Long normalUserId, Long missionId){
        NormalUser normalUser = normalUserRepository.findById(normalUserId).orElseThrow(NoSuchElementException::new);
        StampMission stampMission = stampMissionRepository.findById(missionId).orElseThrow(NoSuchElementException::new);


        CompletedStampMission completedStampMission = new CompletedStampMission();
        completedStampMission.setNormalUser(normalUser);
        completedStampMission.setStampMission(stampMission);

        boolean flag = true;

        try {
            completedStampMissionRepository.save(completedStampMission);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            log.error(" 저장 실패");
            e.printStackTrace();
            flag = false;
        }

        return flag;

    }

    public  List<Long> getCompletedMissionIdsByUserIdAndFestivalId(Long userId, Long festivalId) {
        return completedStampMissionRepository.findCompletedStampMission_idByNormalUser_idAndOrganizedFestival_FestivalId(userId, festivalId );
    }

}
