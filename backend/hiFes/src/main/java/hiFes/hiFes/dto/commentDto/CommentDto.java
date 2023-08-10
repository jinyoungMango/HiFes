package hiFes.hiFes.dto.commentDto;

import hiFes.hiFes.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CommentDto {
    private Long id;
    private String content;
    private Long parentId;
    private LocalDateTime createdAt;
    private List<CommentDto> childComments;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();

        if (comment.getParent() != null) {
            this.parentId = comment.getParent().getId();
        }

        this.childComments = comment.getChild().stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

}
