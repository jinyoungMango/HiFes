package hiFes.hiFes.dto;


import hiFes.hiFes.domain.Marker;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class MarkerResponse {

    private final String boothName;
    private final BigDecimal boothLatitude;
    private final BigDecimal boothLongitude;
    private final String description;
    private final int boothNo;

    public MarkerResponse(Marker marker){
        this.boothLatitude = marker.getBoothLatitude();
        this.boothLongitude = marker.getBoothLongitude();
        this.boothNo = marker.getBoothNo();
        this.boothName = marker.getBoothName();
        this.description = marker.getDescription();

    }
}
