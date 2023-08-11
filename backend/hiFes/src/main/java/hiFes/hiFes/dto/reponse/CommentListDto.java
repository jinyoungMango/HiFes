package hiFes.hiFes.dto.reponse;

import hiFes.hiFes.domain.Comment;
import lombok.Getter;

@Getter
public class CommentListDto {
    private Long id;
    private String content;
    private Comment parent;

    public CommentListDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.parent = comment.getParent();
    }
}
