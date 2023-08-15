package hiFes.hiFes.dto.postDto;


import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostCreateDto {

    private String postType;
    private String title;
    private String content;
    private Float rating;

    private String imagePath;
    private String originalName;
    private String uuid;

    private Long createdBy;
    private Boolean isHidden;

    private Long festivalId;

}
