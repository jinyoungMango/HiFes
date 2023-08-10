package hiFes.hiFes.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(length = 31, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "post_type", nullable = false)
    private String postType;

    private boolean isHidden;

    private String hideReason;

    private int views;

//    @OneToMany(
//            mappedBy = "post",
//            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
//            orphanRemoval = true
//    )
//    private List<Picture> picture = new ArrayList<>();

    private float rating;

    @OneToMany(mappedBy = "post", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Comment> comments;

    public List<Comment> getTopLevelComments() {
        return comments.stream()
                .filter(comment -> comment.getParent() == null)
                .collect(Collectors.toList());
    }

    @Builder
    public Post(Long id, String title, String content, String postType, boolean isHidden,
                String hideReason, float rating, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.isHidden = isHidden;
        this.hideReason = hideReason;
//        this.picture = picture; 위 변수 값에도 추가해야됨
        this.rating = rating;
        this.comments = comments;
    }
//    FK
//    @ManyToOne
//    private Long festivalId;

//    @ManyToOne
//    private Long hostId;

//    @ManyToOne
//    private Long normalUserId

//    private Member createdBy;
    // == 메서드 == //

    public void update(String title, String content, String postType) {
        this.title = title;
        this.content = content;
        this.postType = postType;
    }
//    public void addPicture(Picture picture) {
//        this.picture.add(picture);
//
//        // 게시글에 파일이 없을 경우
//        if (picture.getPost() != this)
//            picture.setPost(this);
//    }

}
