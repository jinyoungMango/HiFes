package hiFes.hiFes.dto.festival;

import hiFes.hiFes.domain.festival.StampMission;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class StampMissionResponse {

    private final Long missionId;
    private final String missionTitle;
    private final String missionOutline;
    private final BigDecimal missionLatitude;
    private final BigDecimal missionLongitude;
    public StampMissionResponse(StampMission stampMission) {
        this.missionId = stampMission.getMissionId();
        this.missionTitle = stampMission.getMissionTitle();
        this.missionLatitude = stampMission.getMissionLatitude();
        this.missionLongitude = stampMission.getMissionLongitude();
        this.missionOutline = stampMission.getMissionOutline();

    }
}
