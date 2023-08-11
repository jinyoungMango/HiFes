package hiFes.hiFes.dto.request;


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
