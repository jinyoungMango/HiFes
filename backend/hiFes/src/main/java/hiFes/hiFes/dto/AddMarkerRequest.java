package hiFes.hiFes.dto;

import hiFes.hiFes.domain.Marker;
import hiFes.hiFes.domain.OrganizedFestival;
import lombok.Getter;

import java.math.BigDecimal;

//@ToString
@Getter
public class AddMarkerRequest {
    private String boothName;
    private BigDecimal boothLatitude;
    private BigDecimal boothLongitude;
    private String description;
    private int boothNo;

    public Marker toEntity(OrganizedFestival organizedFestival){
        return Marker.builder()
                .boothLatitude(boothLatitude)
                .boothName(boothName)
                .boothLongitude(boothLongitude)
                .boothNo(boothNo)
                .description(description)
                .organizedFestival(organizedFestival)
                .build();
    }
}
