package hiFes.hiFes.dto.postDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    @NotNull(message = "Cannot be null")
    private String title;

    @NotNull(message = "Cannot be null")
    private String content;

    @NotNull(message = "Cannot be null")
    private String postType;

    @Builder
    public PostUpdateRequestDto(String title, String content, String postType) {
        this.title = title;
        this.content = content;
        this.postType = postType;
    }
}
