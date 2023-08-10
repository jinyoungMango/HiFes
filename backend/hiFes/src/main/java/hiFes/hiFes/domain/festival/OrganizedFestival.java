package hiFes.hiFes.domain.festival;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import hiFes.hiFes.domain.user.HostUser;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="OrganizedFestival")
public class OrganizedFestival {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="festivalId", updatable = false)
    private Long festivalId;

    @Column(name="fesTitle", nullable = false, length = 31)
    private String fesTitle;

    @Lob
    @Column(name="fesOutline", nullable = false, columnDefinition = "TEXT")
    private String fesOutline;

    @Lob
    @Column(name="fesAddress", nullable = false, columnDefinition = "TINYTEXT")
    private String fesAddress;

    @Schema(description = "이미지 url, 업로드하면 자동 저장", example = "image/이미지.jpg")
    @Column(name="fesPosterPath", nullable = false)
    private String fesPosterPath;

    @Schema(description = "gson 사용으로 넘겨줄 때 string이 아니라 Object로 넘겨야 함", example = "{\"year\":\"2023\", \"month\":\"03\", \"day\":\"01\"}")
    @Column(name="fesStartDate", nullable = false)
    private LocalDate fesStartDate;

    @Schema(description = "gson 사용으로 넘겨줄 때 string이 아니라 Object로 넘겨야 함", example = "{\"year\":\"2023\", \"month\":\"03\", \"day\":\"01\"}")
    @Column(name="fesEndDate", nullable = false)
    private LocalDate fesEndDate;

    @Schema(description = "주소만 입력하면 자동 저장")
    @Column(name = "fesLatitude", nullable = false)
    private BigDecimal fesLatitude;

    @Schema(description = "주소만 입력하면 자동 저장")
    @Column(name="fesLongitude", nullable = false)
    private BigDecimal fesLongitude;

    @JsonManagedReference
    @OneToMany(mappedBy = "organizedFestival", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<FestivalTable> festivalTables = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "organizedFestival", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ARItem> arItems = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "organizedFestival", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<StampMission> stampMissions = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "organizedFestival", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Marker> markers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private HostUser hostUser;

    @Builder
    public OrganizedFestival(HostUser hostUser, String fesTitle, String fesOutline, String fesAddress, String fesPosterPath, LocalDate fesStartDate, LocalDate fesEndDate, BigDecimal fesLatitude, BigDecimal fesLongitude){
        this.fesTitle = fesTitle;
        this.fesLatitude = fesLatitude;
        this.fesAddress = fesAddress;
        this.fesLongitude = fesLongitude;
        this.fesEndDate = fesEndDate;
        this.fesPosterPath = fesPosterPath;
        this.fesOutline = fesOutline;
        this.hostUser = hostUser;
        this.fesStartDate = fesStartDate;

    }

    public void OrganizedFestivalupdate( String fesTitle, String fesOutline, String fesAddress, String fesPosterPath, LocalDate fesStartDate, LocalDate fesEndDate, BigDecimal fesLatitude, BigDecimal fesLongitude){
        this.fesTitle = fesTitle;
//        this.festivalId = festivalId;
        this.fesLatitude = fesLatitude;
        this.fesAddress = fesAddress;
        this.fesLongitude = fesLongitude;
        this.fesEndDate = fesEndDate;
        this.fesPosterPath = fesPosterPath;
        this.fesOutline = fesOutline;
        this.fesStartDate = fesStartDate;
    }




}
