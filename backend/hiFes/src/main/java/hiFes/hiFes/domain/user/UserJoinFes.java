package hiFes.hiFes.domain.user;


import hiFes.hiFes.domain.festival.OrganizedFestival;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UserJoinFes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="festivalId")
    private OrganizedFestival organizedFestival;

    @ManyToOne
    @JoinColumn(name = "normalUserId")
    private NormalUser normalUser;
}
