package hiFes.hiFes.dto.festival;


import hiFes.hiFes.domain.festival.OrganizedFestival;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter

public class SearchOrganizedFestivalResponse{
    private final String searchType;
    private final String fesTitle;
    private final String fesOutline;
    private final String fesAddress;
    private final String fesPosterPath;
    private final LocalDate fesStartDate;
    private final LocalDate fesEndDate;
    private final BigDecimal fesLatitude;
    private final BigDecimal fesLongitude;
    private final Long festivalId;

    public SearchOrganizedFestivalResponse(OrganizedFestival organizedFestival, String searchType){
        this.festivalId = organizedFestival.getFestivalId();
        this.fesTitle = organizedFestival.getFesTitle();
        this.fesAddress = organizedFestival.getFesAddress();
        this.fesEndDate = organizedFestival.getFesEndDate();
        this.fesLatitude = organizedFestival.getFesLatitude();
        this.fesLongitude = organizedFestival.getFesLongitude();
        this.fesOutline = organizedFestival.getFesOutline();
        this.fesPosterPath = organizedFestival.getFesPosterPath();
        this.fesStartDate = organizedFestival.getFesStartDate();
        this.searchType = searchType;
    }


}
