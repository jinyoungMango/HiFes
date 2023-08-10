package hiFes.hiFes.service.festival;


import hiFes.hiFes.domain.festival.CompletedStampMission;
import hiFes.hiFes.domain.festival.OrganizedFestival;
import hiFes.hiFes.domain.festival.ParticipatedFes;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.repository.festival.CompletedStampMissionRepository;
import hiFes.hiFes.repository.festival.OrganizedFestivalRepository;
import hiFes.hiFes.repository.festival.ParticipatedFesRepository;
import hiFes.hiFes.repository.user.NormalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ParticipatedFesService {

    @Autowired
    private final ParticipatedFesRepository participatedFesRepository;
    @Autowired
    private final NormalUserRepository normalUserRepository;
    @Autowired
    private final OrganizedFestivalRepository organizedFestivalRepository;
    private final CompletedStampMissionRepository completedStampMissionRepository;


    @Autowired
    public ParticipatedFesService(NormalUserRepository normalUserRepository,
                                  ParticipatedFesRepository participatedFesRepository,
                                  OrganizedFestivalRepository organizedFestivalRepository,
                                    CompletedStampMissionRepository completedStampMissionRepository){
        this.normalUserRepository = normalUserRepository;
        this.participatedFesRepository = participatedFesRepository;
        this.organizedFestivalRepository = organizedFestivalRepository;
        this.completedStampMissionRepository = completedStampMissionRepository;
    }

    // QR찍으면 참여
    public ParticipatedFes saveParticipatedFes(Long normalUserId, Long festivalId){
        NormalUser normalUser = normalUserRepository.findById(normalUserId).orElseThrow(NoSuchElementException::new);
        OrganizedFestival organizedFestival = organizedFestivalRepository.findById(festivalId).orElseThrow(NoSuchElementException::new);

        ParticipatedFes participatedFes = new ParticipatedFes();
        participatedFes.setNormalUser(normalUser);
        participatedFes.setOrganizedFestival(organizedFestival);
//        participatedFes.setIsCompleted(organizedFestival.getStampMissions().size()==);
        //완료 여부: 행사의 미션 개수 == 이 유저가 달성한 이 행사의 미션 개수.
        return participatedFesRepository.save(participatedFes);


    }

}
