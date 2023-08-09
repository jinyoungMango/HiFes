package hiFes.hiFes.dto;


import hiFes.hiFes.domain.Post;
import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
public class PostCreateDto {

    private String postType;
    private String title;
    private String content;

    @Builder
    public PostCreateDto(String postType,String title, String content) {
        this.postType = postType;
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .postType(postType)
                .title(title)
                .content(content)
                .build();
    }

}
