<<<<<<<< HEAD:backend/hiFes/src/main/java/hiFes/hiFes/dto/reponse/PostListDto.java
package hiFes.hiFes.dto.reponse;
========
package hiFes.hiFes.dto.postDto;
>>>>>>>> 90bac5b3010f0f969136fd409e9f527151aaf6e5:backend/hiFes/src/main/java/hiFes/hiFes/dto/postDto/PostListDto.java

import hiFes.hiFes.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostListDto {
    private Long id;
    private String postType;
    private String title;
    private String content;
//    private LocalDateTime createdAt;

    public PostListDto(Post post) {
        this.id = post.getId();
        this.postType = post.getPostType();
        this.title = post.getTitle();
        this.content = post.getContent();
//        this.createdAt = post.getCreatedAt();
    }
}
