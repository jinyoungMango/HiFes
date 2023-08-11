package hiFes.hiFes.dto.festival;

import hiFes.hiFes.domain.festival.ARItem;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ARItemResponse {

  private final BigDecimal ARLatitude;
  private final BigDecimal ARLongitude;
  private final String ARImage;
  private final String giftInfo;

    public ARItemResponse(ARItem arItem){
        this.ARImage = arItem.getARImage();
        this.ARLongitude = arItem.getARLongitude();
        this.giftInfo = arItem.getGiftInfo();
        this.ARLatitude = arItem.getARLatitude();

    }


}
