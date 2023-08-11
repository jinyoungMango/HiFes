package hiFes.hiFes.dto.postDto;



import hiFes.hiFes.domain.Post;
import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
public class PostCreateDto {

    private String postType;
    private String title;
    private String content;
    private Long createdBy;
    private float rating;

    @Builder
    public PostCreateDto(String postType,String title, String content, Long createdBy, float rating) {
        this.postType = postType;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.rating = rating;
    }

    public Post toEntity() {
        return Post.builder()
                .postType(postType)
                .title(title)
                .content(content)
                .createdBy(createdBy)
                .rating(rating)
                .build();
    }

}
