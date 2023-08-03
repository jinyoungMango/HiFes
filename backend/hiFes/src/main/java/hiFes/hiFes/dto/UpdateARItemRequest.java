package hiFes.hiFes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Getter
@NoArgsConstructor
public class UpdateARItemRequest {

    private BigDecimal arLatitude;
    private BigDecimal arLongitude;
    private String arImage;
    private String giftInfo;
    private Long itemId;

    public UpdateARItemRequest(Long itemId, String giftInfo, String arImage, BigDecimal arLatitude, BigDecimal arLongitude){
        this.arImage = arImage;
        this.arLatitude = arLatitude;
        this.arLongitude = arLongitude;
        this.giftInfo = giftInfo;
        this.itemId = itemId;
    }
}
