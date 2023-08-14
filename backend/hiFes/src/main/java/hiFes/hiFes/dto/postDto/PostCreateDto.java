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
    private Long createdBy;
    private String imagePath;
    private Boolean isHidden;
    private Long festivalId;
    private Float rating;

}
