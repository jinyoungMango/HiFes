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

    @Builder
    public PostCreateDto(String postType,String title, String content, Long createdBy) {
        this.postType = postType;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }

    public Post toEntity() {
        return Post.builder()
                .postType(postType)
                .title(title)
                .content(content)
                .createdBy(createdBy)
                .build();
    }

}
