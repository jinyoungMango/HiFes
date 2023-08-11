<<<<<<<< HEAD:backend/hiFes/src/main/java/hiFes/hiFes/dto/reponse/PostDto.java
package hiFes.hiFes.dto.reponse;
========
package hiFes.hiFes.dto.postDto;
>>>>>>>> 90bac5b3010f0f969136fd409e9f527151aaf6e5:backend/hiFes/src/main/java/hiFes/hiFes/dto/postDto/PostDto.java

import hiFes.hiFes.domain.BaseEntity;
import hiFes.hiFes.domain.Post;
<<<<<<<< HEAD:backend/hiFes/src/main/java/hiFes/hiFes/dto/reponse/PostDto.java
import hiFes.hiFes.dto.reponse.CommentListDto;
========
import hiFes.hiFes.dto.commentDto.CommentDto;
>>>>>>>> 90bac5b3010f0f969136fd409e9f527151aaf6e5:backend/hiFes/src/main/java/hiFes/hiFes/dto/postDto/PostDto.java
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
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private int views;

    private List<CommentDto> topLevelComments;

    public PostDto(Post post) {
        this.id = post.getId();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.postType = post.getPostType();
        this.isHidden = post.isHidden();
        this.hideReason = post.getHideReason();
        this.topLevelComments = post.getTopLevelComments().stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());

    }
}
