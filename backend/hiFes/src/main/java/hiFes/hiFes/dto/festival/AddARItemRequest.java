package hiFes.hiFes.dto.festival;

import hiFes.hiFes.domain.festival.ARItem;
import hiFes.hiFes.domain.festival.OrganizedFestival;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

//@ToString
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddARItemRequest {
    private BigDecimal arLatitude;
    private BigDecimal arLongitude;
    private String arImage;
    private String giftInfo;

//    private List<AddARItemRequest> arItems;



//    @Override
//    public String toString() {
//        return "AddARItemRequest{" +
//                "ARLatitude=" + ARLatitude +
//                ", ARLongitude=" + arLongitude +
//                ", ARImage='" + arImage + '\'' +
//                ", giftInfo='" + giftInfo + '\'' +
//                '}';
//    }

    public ARItem toEntity(OrganizedFestival organizedFestival){
        return ARItem.builder()
                .ARLongitude(arLongitude)
                .ARLatitude(arLatitude)
                .ARImage(arImage)
                .giftInfo(giftInfo)
                .organizedFestival(organizedFestival)
                .build();
    }

}
