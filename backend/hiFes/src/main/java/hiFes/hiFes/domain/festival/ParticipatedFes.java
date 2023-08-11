package hiFes.hiFes.domain.festival;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hiFes.hiFes.domain.user.HostUser;
import hiFes.hiFes.domain.user.NormalUser;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="ParticipatedFes")
public class ParticipatedFes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="normal_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private NormalUser normalUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="festivalId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private OrganizedFestival organizedFestival;

    @Column(name="isCompleted", nullable = false)
    private Boolean isCompleted;

//    @CreatedDate
    @Column(name="participateTime", nullable = false)
    private LocalDateTime participateTime;


    @Column(name = "countMission", nullable = false)
    public Long countMission;

    @Builder
    public ParticipatedFes(LocalDateTime participateTime, Boolean isCompleted, Long countMission){
        this.participateTime = participateTime;
        this.isCompleted = isCompleted;
        this.countMission = countMission;
    }
}
