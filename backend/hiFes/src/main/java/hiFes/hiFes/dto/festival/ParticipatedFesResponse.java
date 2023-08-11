package hiFes.hiFes.dto.festival;

import hiFes.hiFes.domain.festival.ParticipatedFes;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipatedFesResponse {
    private final Long festivalId;
    private final Long normalUserId;
    private final Boolean isCompleted;
    private final String fesTitle;
    private final Integer countMission;


    public ParticipatedFesResponse(ParticipatedFes participatedFes){
        this.fesTitle = participatedFes.getOrganizedFestival().getFesTitle();
        this.festivalId = participatedFes.getOrganizedFestival().getFestivalId();
        this.normalUserId = participatedFes.getNormalUser().getId();
        this.isCompleted = participatedFes.getIsCompleted();
        this.countMission = participatedFes.getOrganizedFestival().getStampMissions().size();
    }

}