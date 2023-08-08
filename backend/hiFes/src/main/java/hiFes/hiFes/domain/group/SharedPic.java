package hiFes.hiFes.domain.group;

import hiFes.hiFes.domain.group.Group;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SharedPic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    private String sharedPic;
}
