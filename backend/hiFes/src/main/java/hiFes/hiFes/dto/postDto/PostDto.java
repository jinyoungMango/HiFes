package hiFes.hiFes.dto.postDto;


import hiFes.hiFes.domain.BaseEntity;
import hiFes.hiFes.domain.Post;
import hiFes.hiFes.dto.commentDto.CommentDto;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PostDto {
    private Long id;
    private Long createdBy;
//    private String writer;
    private String title;
    private String content;
    private String postType;
    private Boolean isHidden;
    private String hideReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int views;
    private float rating;

    private List<CommentDto> topLevelComments;

    public PostDto(Post post) {
        this.id = post.getId();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.createdBy = post.getCreatedBy();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postType = post.getPostType();
        this.views = post.getViews();
        this.rating = post.getRating();
        this.isHidden = post.getIsHidden();
        this.hideReason = post.getHideReason();
        this.topLevelComments = post.getTopLevelComments().stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());

    }
}
