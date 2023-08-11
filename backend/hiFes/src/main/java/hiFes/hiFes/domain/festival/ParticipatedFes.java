package hiFes.hiFes.domain.festival;

import com.fasterxml.jackson.annotation.JsonBackReference;
import hiFes.hiFes.domain.user.NormalUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Participated")
public class ParticipatedFes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="normalUser_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private NormalUser normalUser;

    @ManyToOne
    @JoinColumn(name="festivalId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private OrganizedFestival organizedFestival;

    @Column(name="isCompleted", nullable = false)
    private Boolean isCompleted;

    @CreatedDate
    @Column(name="participateTime", nullable = false)
    private LocalDateTime participateTime;


}
