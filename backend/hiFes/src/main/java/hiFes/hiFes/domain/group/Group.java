package hiFes.hiFes.domain.group;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Entity
@Builder
@Table(name = "groups")
@AllArgsConstructor
@NoArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "group_name")
    private String groupName;
    private String groupPic;
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    private Integer maxPop;
    private String content;
    private BigDecimal getterLatitude;
    private BigDecimal getterLongitude;
    private String getterOutline;
    private Long festivalId;
}
