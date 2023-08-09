package hiFes.hiFes.dto;

import hiFes.hiFes.domain.Post;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDto {
    private Long id;
    private String title;
    private String content;
    private String postType;
    private boolean isHidden;

    private String hideReason;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private int views;

    private List<CommentListDto> comments;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postType = post.getPostType();
        this.isHidden = post.isHidden();
        this.hideReason = post.getHideReason();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.comments = post.getComments().stream()
                .map(CommentListDto::new)
                .collect(Collectors.toList());

    }
}
