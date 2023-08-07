package hiFes.hiFes.dto;


import hiFes.hiFes.domain.OrganizedFestival;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter

public class OrganizedFestivalResponse {
    private final String fesTitle;
    private final String fesOutline;
    private final String fesAddress;
    private final String fesPosterPath;
    private final LocalDate fesStartDate;
    private final LocalDate fesEndDate;
    private final BigDecimal fesLatitude;
    private final BigDecimal fesLongitude;


    public OrganizedFestivalResponse(OrganizedFestival organizedFestival){
        this.fesTitle = organizedFestival.getFesTitle();
        this.fesAddress = organizedFestival.getFesAddress();
        this.fesEndDate = organizedFestival.getFesEndDate();
        this.fesLatitude = organizedFestival.getFesLatitude();
        this.fesLongitude = organizedFestival.getFesLongitude();
        this.fesOutline = organizedFestival.getFesOutline();
        this.fesPosterPath = organizedFestival.getFesPosterPath();
        this.fesStartDate = organizedFestival.getFesStartDate();
    }
}
