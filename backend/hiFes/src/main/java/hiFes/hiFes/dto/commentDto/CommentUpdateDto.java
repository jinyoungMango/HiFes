<<<<<<<< HEAD:backend/hiFes/src/main/java/hiFes/hiFes/dto/request/CommentUpdateDto.java
package hiFes.hiFes.dto.request;
========
package hiFes.hiFes.dto.commentDto;
>>>>>>>> 90bac5b3010f0f969136fd409e9f527151aaf6e5:backend/hiFes/src/main/java/hiFes/hiFes/dto/commentDto/CommentUpdateDto.java

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
