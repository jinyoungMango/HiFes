package hiFes.hiFes.dto.festival;

import hiFes.hiFes.domain.festival.StampMission;
import lombok.Getter;

import java.math.BigDecimal;

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
