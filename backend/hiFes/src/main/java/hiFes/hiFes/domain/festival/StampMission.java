package hiFes.hiFes.domain.festival;


import com.fasterxml.jackson.annotation.JsonBackReference;
import hiFes.hiFes.dto.festival.UpdateStampMissionRequest;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="StampMission")
public class StampMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "missionId", updatable = false)
    private Long missionId;

    @Column(name = "missionTitle", nullable = false, length = 31)
    private String missionTitle;

    @Column(name = "missionOutline", nullable = false)
    private String missionOutline;

    @Column(name = "missionLatitude", nullable = false)
    private BigDecimal missionLatitude;

    @Column(name = "missionLongitude", nullable = false)
    private BigDecimal missionLongitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festivalId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference

    private OrganizedFestival organizedFestival;



    @Builder
    public StampMission(String missionTitle, String missionOutline, BigDecimal missionLatitude, BigDecimal missionLongitude,
                        OrganizedFestival organizedFestival) {
        this.missionTitle = missionTitle;
        this.missionLatitude = missionLatitude;
        this.missionLongitude = missionLongitude;
        this.organizedFestival = organizedFestival;
        this.missionOutline = missionOutline;
    }

    public void update(UpdateStampMissionRequest request) {
//        this.missionId = request.getMissionId();
        this.missionTitle = request.getMissionTitle();
        this.missionLatitude = request.getMissionLatitude();
        this.missionLongitude = request.getMissionLongitude();
//        this.organizedFestival = request.getorganizedFestival;
        this.missionOutline = request.getMissionOutline();

    }
}
