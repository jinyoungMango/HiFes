package hiFes.hiFes.domain;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import hiFes.hiFes.dto.UpdateFestivalTableRequest;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name="FestivalTable")
public class FestivalTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="programId", updatable = false)
    private Long programId;

    @Column(name="programTitle", nullable = false, length = 31)
    private String programTitle;

    private String programOutline;

    @Column(name="startTime", nullable = false)
    private LocalDateTime startTime;

    @Column(name="endTime", nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festivalId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference

    private OrganizedFestival organizedFestival;

    @JsonManagedReference
    @OneToMany(mappedBy = "eventNotificationId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventNotification> eventNotificationList = new ArrayList<>();

    @Builder
    public FestivalTable(String programOutline, String programTitle, LocalDateTime startTime, LocalDateTime endTime,OrganizedFestival organizedFestival){
        this.endTime = endTime;
        this.programOutline = programOutline;
        this.programTitle = programTitle;
        this.startTime = startTime;
        this.organizedFestival = organizedFestival;
    }

    public void update(UpdateFestivalTableRequest request){
        this.programId = request.getProgramId();
        this.programOutline = request.getProgramOutline();
        this.programTitle = request.getProgramTitle();
        this.startTime = request.getStartTime();
        this.endTime = request.getEndTime();
//        this.organizedFestival = organizedFestival;
    }


}
