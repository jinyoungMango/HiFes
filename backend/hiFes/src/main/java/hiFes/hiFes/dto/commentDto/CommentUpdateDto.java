package hiFes.hiFes.dto.commentDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentUpdateDto {
    private String content;

    @Builder
    public CommentUpdateDto(String content) {
        this.content = content;
    }
}
