package hiFes.hiFes.dto.postDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    @NotNull(message = "Cannot be null")
    private String title;
    @NotNull(message = "Cannot be null")
    private String content;
    @NotNull(message = "Cannot be null")
    private String postType;

    private Long createdBy;

    private Float rating;
    private Boolean isHidden;

    @Builder
    public PostUpdateRequestDto(String title, String content, String postType, Long createdBy) {
        this.title = title;
        this.content = content;
        this.postType = postType;
        this.createdBy = createdBy;
    }
}
