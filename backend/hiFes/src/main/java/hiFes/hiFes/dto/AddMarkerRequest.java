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
    private String showingImg;
    private String description;
    private int boothNo;
    private String markerColor;

    public Marker toEntity(OrganizedFestival organizedFestival){
        return Marker.builder()
                .boothLatitude(boothLatitude)
                .boothName(boothName)
                .boothLongitude(boothLongitude)
                .boothNo(boothNo)
                .showingImg(showingImg)
                .description(description)
                .markerColor(markerColor)
                .organizedFestival(organizedFestival)
                .build();
    }
}
