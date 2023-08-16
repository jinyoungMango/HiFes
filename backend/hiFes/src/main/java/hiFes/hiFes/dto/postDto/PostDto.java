package hiFes.hiFes.dto.postDto;


import hiFes.hiFes.domain.BaseEntity;
import hiFes.hiFes.domain.Post;
import hiFes.hiFes.dto.commentDto.CommentDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    private Long createdBy;
    private String writer;

    private String title;
    private String content;
    private String postType;
    private Boolean isHidden;
    private String hideReason;

    private String imagePath;

    private Long organizedFestivalId;

    private int views;
    private Float rating;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<CommentDto> topLevelComments;
    private int commentsCount;


}
