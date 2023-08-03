package hiFes.hiFes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class UpdateMarkerRequest {
    private String boothName;
    private BigDecimal boothLatitude;
    private BigDecimal boothLongitude;
    private String showingImg;
    private String description;
    private int boothNo;
    private String markerColor;
    private Long markerId;


    public UpdateMarkerRequest(Long markerId, String boothName, String description, String markerColor,
                               String showingImg, BigDecimal boothLongitude, BigDecimal boothLatitude,
                               int boothNo){
        this.boothLatitude = boothLatitude;
        this.boothLongitude = boothLongitude;
        this.markerColor = markerColor;
        this.boothName = boothName;
        this.showingImg =showingImg;
        this.description = description;
        this.boothNo = boothNo;
        this.markerId = markerId;
    }

}
