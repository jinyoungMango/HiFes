package hiFes.hiFes.dto;

import hiFes.hiFes.domain.CompletedStampMission;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompletedStampMissionResponse {
    private final Long missionId;
    private final Long normalUserId;

    public CompletedStampMissionResponse(CompletedStampMission completedStampMission) {
        this.missionId = completedStampMission.getStampMission().getMissionId();
        this.normalUserId = completedStampMission.getNormalUser().getId();
    }

}
