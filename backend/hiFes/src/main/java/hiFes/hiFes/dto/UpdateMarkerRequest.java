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
    private String description;
    private int boothNo;
    private Long markerId;


    public UpdateMarkerRequest(Long markerId, String boothName, String description,
                               BigDecimal boothLongitude, BigDecimal boothLatitude,
                               int boothNo){
        this.boothLatitude = boothLatitude;
        this.boothLongitude = boothLongitude;
        this.boothName = boothName;
        this.description = description;
        this.boothNo = boothNo;
        this.markerId = markerId;
    }

}
