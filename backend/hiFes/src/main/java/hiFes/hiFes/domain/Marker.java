package hiFes.hiFes.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import hiFes.hiFes.dto.UpdateMarkerRequest;
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
@Table(name="Marker")
public class Marker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="markerId", updatable = false)
    private Long markerId;

    @Column(name="boothName", nullable = false, length = 15)
    private String boothName;

    @Column(name="boothLatitude", nullable = false)
    private BigDecimal boothLatitude;

    @Column(name="boothLongitude", nullable = false)
    private BigDecimal boothLongitude;

    @Column(name="showingImg", nullable = false)
    private String showingImg;

    @Column(name="description")
    private String description;

    @Column(name="boothNo", nullable = false)
    private Integer boothNo;

    @Column(name="markerColor", nullable = false, length = 15)
    private String markerColor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festivalId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private OrganizedFestival organizedFestival;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "hostId")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private Host host;

    @Builder
    public Marker(BigDecimal boothLatitude, BigDecimal boothLongitude, String boothName,
                  String showingImg, String description, Integer boothNo, String markerColor, OrganizedFestival organizedFestival){
        this.boothLatitude =boothLatitude;
        this.boothLongitude = boothLongitude;
        this.boothName = boothName;
        this.description =description;
        this.boothNo = boothNo;
        this.showingImg = showingImg;
        this.organizedFestival = organizedFestival;
        this.markerColor = markerColor;
    }

    public void update(UpdateMarkerRequest request){
        this.markerId =request.getMarkerId();
        this.boothLatitude =request.getBoothLatitude();
        this.boothLongitude = request.getBoothLongitude();
        this.boothName = request.getBoothName();
        this.description = request.getDescription();
        this.boothNo = request.getBoothNo();
        this.showingImg = request.getShowingImg();
//        this.organizedFestival = organizedFestival;
        this.markerColor = request.getMarkerColor();
    }
}
