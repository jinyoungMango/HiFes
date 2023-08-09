package hiFes.hiFes.domain.group;

import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.group.Hashtag;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class RegisteredHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "groupId")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "hashtagId")
    private Hashtag hashtag;
}
