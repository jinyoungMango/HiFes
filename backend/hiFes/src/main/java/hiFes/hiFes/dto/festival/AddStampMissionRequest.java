package hiFes.hiFes.dto.festival;

import hiFes.hiFes.domain.festival.OrganizedFestival;
import hiFes.hiFes.domain.festival.StampMission;
import lombok.Getter;

import java.math.BigDecimal;

//@ToString
@Getter
public class AddStampMissionRequest {
    private String missionTitle;
    private String missionOutline;
    private BigDecimal missionLatitude;
    private BigDecimal missionLongitude;

//    public AddStampMissionRequest(StampMission stampMission) {
//        this.missionTitle = stampMission.getMissionTitle();
//        this.missionOutline = stampMission.getMissionOutline();
//        this.missionLatitude = stampMission.getMissionLatitude();
//        this.missionLongitude = stampMission.getMissionLongitude();
//    }

    public StampMission toEntity(OrganizedFestival organizedFestival){
        return StampMission.builder()
                .missionLatitude(missionLatitude)
                .missionLongitude(missionLongitude)
                .missionTitle(missionTitle)
                .missionOutline(missionOutline)
                .organizedFestival(organizedFestival)
                .build();
    }
}
