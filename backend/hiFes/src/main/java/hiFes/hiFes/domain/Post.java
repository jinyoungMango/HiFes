package hiFes.hiFes.domain;

import hiFes.hiFes.domain.festival.OrganizedFestival;
import hiFes.hiFes.domain.user.HostUser;
import hiFes.hiFes.domain.user.NormalUser;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

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
    //    FK
    @ManyToOne(fetch = LAZY)
    @JoinColumn
    private OrganizedFestival organizedFestival;

    @Column(columnDefinition = "TEXT")
    private Long createdBy;

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
    public Post(Long id, Long createdBy, String title, String content, String postType, boolean isHidden,
                String hideReason, float rating, List<Comment> comments) {
        this.id = id;
        this.createdBy = createdBy;
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.isHidden = isHidden;
        this.hideReason = hideReason;
//        this.picture = picture; 위 변수 값에도 추가해야됨
        this.rating = rating;
        this.comments = comments;
    }


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
