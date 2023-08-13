package hiFes.hiFes.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.MERGE, targetEntity = Post.class)
    @JoinColumn(name = "postId")
    private Post post;

    @Column(columnDefinition = "TEXT")
    private Long createdBy;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parentId")
    @Setter
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    // == 연관관계 메서드 == //
    public void addChildComment(Comment child) {
        this.child.add(child);
        child.setParent(this);
    }

//    @OneToMany(mappedBy = "parent")
//    private List<Comment> child;

    public List<Comment> getChildComments() {
        return child;
    }


    // 빌더
    @Builder
    public Comment(Post post, String content, Comment parent, Long createdBy) {
        this.post = post;
        this.content = content;
        this.parent = parent;
        this.createdBy = createdBy;
    }

    // 메서드
    public void update(String content) {
        this.content = content;
    }

}
