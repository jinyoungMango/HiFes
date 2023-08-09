package hiFes.hiFes.dto;

import hiFes.hiFes.domain.StampMission;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class StampMissionResponse {


    private final String missionTitle;
    private final String missionOutline;
    private final BigDecimal missionLatitude;
    private final BigDecimal missionLongitude;
    public StampMissionResponse(StampMission stampMission) {

        this.missionTitle = stampMission.getMissionTitle();
        this.missionLatitude = stampMission.getMissionLatitude();
        this.missionLongitude = stampMission.getMissionLongitude();
        this.missionOutline = stampMission.getMissionOutline();

    }
}
