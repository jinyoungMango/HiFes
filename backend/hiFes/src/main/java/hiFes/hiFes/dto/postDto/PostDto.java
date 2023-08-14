package hiFes.hiFes.dto.postDto;


import hiFes.hiFes.domain.BaseEntity;
import hiFes.hiFes.domain.Post;
import hiFes.hiFes.dto.commentDto.CommentDto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class PostDto {
    private Long id;
    private Long createdBy;
    private String title;
    private String content;
    private String postType;
    private Boolean isHidden;
    private String hideReason;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long organizedFestivalId;

    private int views;
    private float rating;

    private List<CommentDto> topLevelComments;
    private int commentsCount;


}
