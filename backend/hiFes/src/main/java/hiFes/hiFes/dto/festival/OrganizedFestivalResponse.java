package hiFes.hiFes.dto.festival;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hiFes.hiFes.domain.festival.OrganizedFestival;
import hiFes.hiFes.domain.user.HostUser;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizedFestivalResponse {
    private final Long festivalId;
    private final String fesTitle;
    private final String fesOutline;
    private final String fesAddress;
    private final String fesPosterPath;
    private final LocalDate fesStartDate;
    private final LocalDate fesEndDate;
    private final BigDecimal fesLatitude;
    private final BigDecimal fesLongitude;

    private final String hostName;
    private final String hostPhoneNo;

    private final Float avgRating;
    private final Integer countGroups;
    private final boolean isUserJoined;
    private final Long groupId;
    private final boolean isFollowed;


    public OrganizedFestivalResponse(OrganizedFestival organizedFestival,Float avgRating, Integer countGroups, boolean isFollowed, boolean isUserJoined, Long groupId){
        this.festivalId = organizedFestival.getFestivalId();
        this.fesTitle = organizedFestival.getFesTitle();
        this.fesAddress = organizedFestival.getFesAddress();
        this.fesEndDate = organizedFestival.getFesEndDate();
        this.fesLatitude = organizedFestival.getFesLatitude();
        this.fesLongitude = organizedFestival.getFesLongitude();
        this.fesOutline = organizedFestival.getFesOutline();
        this.fesPosterPath = organizedFestival.getFesPosterPath();
        this.fesStartDate = organizedFestival.getFesStartDate();
        this.avgRating = avgRating;
        this.countGroups = countGroups;

        HostUser hostUser = organizedFestival.getHostUser();
        this.hostName = hostUser.getName();
        this.hostPhoneNo = hostUser.getPhoneNo();

        this.isFollowed = isFollowed;
        this.isUserJoined = isUserJoined;
        this.groupId = isUserJoined? groupId:null;


    }
}
