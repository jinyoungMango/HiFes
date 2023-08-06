package hiFes.hiFes.domain;



import com.fasterxml.jackson.annotation.JsonBackReference;
import hiFes.hiFes.dto.UpdateARItemRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name="ARItem")
public class ARItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="itemId", updatable = false)
    private Long itemId;

    @Column(name="ARLatitude", nullable = false)
//    @Column(name="ARLatitude")
    private BigDecimal ARLatitude;

    @Column(name="ARLongitude", nullable = false)
//    @Column(name="ARLongitude")
    private BigDecimal ARLongitude;

    @Column(name="ARImage", nullable = false)
    private String ARImage;

    @Lob
    @Column(name="fesAddress", nullable = false, columnDefinition = "TINYTEXT")
    private String giftInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festivalId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private OrganizedFestival organizedFestival;

    @Builder
    public ARItem(BigDecimal ARLatitude, BigDecimal ARLongitude, String ARImage, String giftInfo, OrganizedFestival organizedFestival){
        this.ARLatitude = ARLatitude;
        this.ARLongitude =ARLongitude;
        this.ARImage = ARImage;
        this.giftInfo = giftInfo;
        this.organizedFestival = organizedFestival;
    }

    public void update(UpdateARItemRequest request){
        this.ARLatitude = request.getArLatitude();
        this.ARImage = request.getArImage();
        this.giftInfo = request.getGiftInfo();
        this.ARLongitude = request.getArLongitude();
        this.itemId = request.getItemId();


    }

}
