package hiFes.hiFes.dto.postDto;

import hiFes.hiFes.domain.Post;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class PostUpdateResponseDto {
    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String postType;

    private Long createdBy;

    private Float rating;
    private Boolean isHidden;

    public PostUpdateResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postType = post.getPostType();
        this.rating = post.getRating();
        this.isHidden = post.getIsHidden();
        this.createdBy = post.getCreatedBy();
    }
}
