package hiFes.hiFes.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import hiFes.hiFes.domain.user.NormalUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

//중간 객체 이벤트 일정-회원
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="EventNotification")
public class EventNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="eventNotificationId")
    private Long eventNotificationId;

    @ManyToOne
    @JoinColumn(name="programId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private FestivalTable festivalTable;

    @ManyToOne
    @JoinColumn(name="normalUserId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private NormalUser normalUser;
}
