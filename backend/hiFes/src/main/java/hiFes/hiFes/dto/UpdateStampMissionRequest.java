package hiFes.hiFes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class UpdateStampMissionRequest {
    private Long missionId;
    private String missionTitle;
    private String missionOutline;
    private BigDecimal missionLatitude;
    private BigDecimal missionLongitude;


    public UpdateStampMissionRequest(Long missionId, String missionTitle, String missionOutline, BigDecimal missionLatitude, BigDecimal missionLongitude) {
        this.missionId = missionId;
        this.missionTitle = missionTitle;
        this.missionOutline = missionOutline;
        this.missionLatitude = missionLatitude;
        this.missionLongitude = missionLongitude;
    }
}
