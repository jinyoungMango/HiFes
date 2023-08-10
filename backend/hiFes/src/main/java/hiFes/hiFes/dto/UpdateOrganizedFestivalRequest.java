package hiFes.hiFes.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import hiFes.hiFes.domain.OrganizedFestival;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
//@AllArgsConstructor
@Getter
@Setter
public class UpdateOrganizedFestivalRequest {
    private Long festivalId;
    private String fesTitle;
    private String fesOutline;
    private String fesAddress;

    private String fesPosterPath;
    private LocalDate fesStartDate;
    private LocalDate fesEndDate;
    private BigDecimal fesLatitude;
    private BigDecimal fesLongitude;
    private List<UpdateStampMissionRequest> stampMissions;
    private List<UpdateARItemRequest> items;
    private List<UpdateFestivalTableRequest> festivalTables;
    private List<UpdateMarkerRequest> markers;


    public UpdateOrganizedFestivalRequest(Long festivalId, String fesAddress, String fesOutline, String fesPosterPath,
                                          String fesTitle, LocalDate fesEndDate, LocalDate fesStartDate, BigDecimal fesLatitude, BigDecimal fesLongitude,
                                          List<UpdateStampMissionRequest> stampMissions,
                                          List<UpdateARItemRequest> items,
                                          List<UpdateFestivalTableRequest> festivalTables,
                                          List<UpdateMarkerRequest> markers) {
        this.festivalId = festivalId;
        this.fesEndDate = fesEndDate;
        this.fesAddress = fesAddress;
        this.fesLatitude = fesLatitude;
        this.fesLongitude = fesLongitude;
        this.fesPosterPath = fesPosterPath;
        this.fesOutline = fesOutline;
        this.fesTitle = fesTitle;
        this.fesStartDate = fesStartDate;
        this.stampMissions = stampMissions;
        this.items = items;
        this.festivalTables = festivalTables;
        this.markers = markers;
    }
}
