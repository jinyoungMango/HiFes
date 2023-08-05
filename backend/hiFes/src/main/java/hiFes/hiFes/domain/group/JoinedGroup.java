package hiFes.hiFes.domain.group;

import hiFes.hiFes.domain.user.NormalUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class JoinedGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean isLeader;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "normalUserId")
    private NormalUser normalUser;
}
