package hiFes.hiFes.dto.reponse;

import hiFes.hiFes.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostListDto {
    private Long id;
    private String title;
    private String content;

    public PostListDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }
}
