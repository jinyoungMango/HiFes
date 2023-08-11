package hiFes.hiFes.domain.group;

import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.user.NormalUser;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SharedPic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    private String sharedPic;


    @ManyToOne
    @JoinColumn(name = "normal_user_id")
    private NormalUser normalUser;
}
