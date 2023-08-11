package hiFes.hiFes.dto.commentDto;

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
