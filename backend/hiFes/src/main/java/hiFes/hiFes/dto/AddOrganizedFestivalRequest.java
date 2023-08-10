package hiFes.hiFes.dto;


import hiFes.hiFes.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddOrganizedFestivalRequest {
    private String fesTitle;
    private String fesOutline;
    private String fesAddress;
    private String fesPosterPath;
    private LocalDate fesStartDate;
    private LocalDate fesEndDate;
    private BigDecimal fesLatitude;
    private BigDecimal fesLongitude;
    private List<AddFestivalTableRequest> festivalTables;
    private List<AddARItemRequest> items;
    private List<AddStampMissionRequest> stampMissions;
    private List<AddMarkerRequest> markers;


    // 행사 추가할 때 저장할 엔티티로 변환. 컨트롤러 단에서 함께보내고-> 저장 서비스 로직은 따로.
    public OrganizedFestival toEntity(){
        return OrganizedFestival.builder()
                .fesTitle(fesTitle)
                .fesOutline(fesOutline)
                .fesAddress(fesAddress)
                .fesLatitude(fesLatitude)
                .fesLongitude(fesLongitude)
                .fesPosterPath(fesPosterPath)
                .fesEndDate(fesEndDate)
                .fesStartDate(fesStartDate)
                .build();
    }


}
