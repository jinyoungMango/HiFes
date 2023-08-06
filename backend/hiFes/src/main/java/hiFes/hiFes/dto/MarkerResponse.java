package hiFes.hiFes.dto;


import hiFes.hiFes.domain.Marker;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MarkerResponse {

    private final String boothName;
    private final BigDecimal boothLatitude;
    private final BigDecimal boothLongitude;
    private final String showingImg;
    private final String description;
    private final int boothNo;
    private final String markerColor;

    public MarkerResponse(Marker marker){
        this.boothLatitude = marker.getBoothLatitude();
        this.boothLongitude = marker.getBoothLongitude();
        this.markerColor = marker.getMarkerColor();
        this.boothNo = marker.getBoothNo();
        this.boothName = marker.getBoothName();
        this.description = marker.getDescription();
        this.showingImg = marker.getShowingImg();

    }
}
