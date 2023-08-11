package hiFes.hiFes.dto.postDto;

import hiFes.hiFes.domain.Post;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class PostUpdateResponseDto {
    private Long id;

    @NotNull(message = "Cannot be null")
    private String title;
    @NotNull(message = "Cannot be null")
    private String content;
    @NotNull(message = "Cannot be null")
    private String postType;

    public PostUpdateResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postType = post.getPostType();
    }
}
