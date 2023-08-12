package hiFes.hiFes.dto.postDto;

import hiFes.hiFes.domain.Post;
import hiFes.hiFes.dto.festival.OrganizedFestivalResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostListDto {
    private Long id;
    private String postType;
    private String title;
    private String content;

    private Long festivalId;

    private Long createdBy;
    private int views;

    private LocalDateTime createdAt;


    public PostListDto(Post post) {
        this.id = post.getId();
        this.postType = post.getPostType();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.festivalId = post.getOrganizedFestival().getFestivalId();
        this.createdBy = post.getCreatedBy();
        this.views = post.getViews();
        this.createdAt = post.getCreatedAt();
    }
}
