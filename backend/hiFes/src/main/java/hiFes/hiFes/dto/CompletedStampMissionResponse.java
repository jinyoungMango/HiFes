package hiFes.hiFes.dto;

import hiFes.hiFes.domain.CompletedStampMission;
import hiFes.hiFes.domain.FestivalTable;
import hiFes.hiFes.domain.NormalUser;
import hiFes.hiFes.domain.StampMission;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CompletedStampMissionResponse {
    private final Long missionId;
    private final Long normalUserId;

    public CompletedStampMissionResponse(CompletedStampMission completedStampMission) {
        this.missionId = completedStampMission.getStampMission().getMissionId();
        this.normalUserId = completedStampMission.getNormalUser().getNormalUserId();
    }

}
