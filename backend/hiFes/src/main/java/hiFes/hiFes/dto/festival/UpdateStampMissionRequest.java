package hiFes.hiFes.dto.festival;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
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
