package hiFes.hiFes.dto.festival;

import hiFes.hiFes.domain.festival.CompletedStampMission;
import hiFes.hiFes.domain.festival.OrganizedFestival;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompletedStampMissionResponse {
    private final Long festivalId;
    private List<Long> missionId;

    public CompletedStampMissionResponse(Long festivalId, List<Long> missionId) {
        this.missionId = missionId;
        this.festivalId = festivalId;
    }

}
