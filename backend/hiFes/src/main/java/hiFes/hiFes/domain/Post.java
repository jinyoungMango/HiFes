package hiFes.hiFes.domain;

import hiFes.hiFes.domain.festival.OrganizedFestival;
import hiFes.hiFes.dto.postDto.PostCreateDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "Post")
public class Post extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postId", nullable = false)
    private Long id;

    @Column(length = 31, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(name = "postType", nullable = false)
    private String postType;

    @Column(name = "isHidden")
    private Boolean isHidden;

    @Column(name = "hideReason")
    private String hideReason;

    //    FK
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "festivalId")
    private OrganizedFestival organizedFestival;

    @Column(columnDefinition = "TEXT", name = "createdBy")
    private Long createdBy;

    private int views;

    private Float rating;

    private String imagePath;
//    private String originalName;
//    private String uuid;

    @OneToMany(mappedBy = "post", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<Comment> comments;

    public List<Comment> getTopLevelComments() {
        return comments.stream()
                .filter(comment -> comment.getParent() == null)
                .collect(Collectors.toList());
    }


    public static Post toEntity(PostCreateDto postCreateDto, OrganizedFestival organizedFestival) {
        return Post.builder()
                .createdBy(postCreateDto.getCreatedBy())
                .title(postCreateDto.getTitle())
                .content(postCreateDto.getContent())
                .postType(postCreateDto.getPostType())
                .isHidden(postCreateDto.getIsHidden())
                .rating(postCreateDto.getRating())
                .imagePath(postCreateDto.getImagePath())
//                .originalName(postCreateDto.getOriginalName())
//                .uuid(postCreateDto.getUuid())
                .organizedFestival(organizedFestival)
                .build();
    }


    // == 메서드 == //

    public void update(String title, String content, String postType) {
        this.title = title;
        this.content = content;
        this.postType = postType;
    }

    public void increaseView() {
        this.views += 1;
    }

}
