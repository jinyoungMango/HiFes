<<<<<<<< HEAD:backend/hiFes/src/main/java/hiFes/hiFes/dto/reponse/CommentListDto.java
package hiFes.hiFes.dto.reponse;
========
package hiFes.hiFes.dto.commentDto;
>>>>>>>> 90bac5b3010f0f969136fd409e9f527151aaf6e5:backend/hiFes/src/main/java/hiFes/hiFes/dto/commentDto/CommentListDto.java

import hiFes.hiFes.domain.Comment;
import lombok.Getter;

@Getter
public class CommentListDto {
    private Long id;
    private String content;
    private CommentListDto parent;

    public CommentListDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();

        if (comment.getParent() != null) {
            this.parent = new CommentListDto(comment.getParent());
        }
    }
}
