<<<<<<<< HEAD:backend/hiFes/src/main/java/hiFes/hiFes/dto/request/CommentCreateDto.java
package hiFes.hiFes.dto.request;
========
package hiFes.hiFes.dto.commentDto;
>>>>>>>> 90bac5b3010f0f969136fd409e9f527151aaf6e5:backend/hiFes/src/main/java/hiFes/hiFes/dto/commentDto/CommentCreateDto.java

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentCreateDto {
    private Long postId;
    private String content;
    private Long parentId;
    private Long createdBy;

    @Builder
    public CommentCreateDto(Long postId, String content, Long parentId, Long createdBy) {
        this.postId = postId;
        this.content = content;
        this.parentId = parentId;
        this.createdBy = createdBy;
    }

}
