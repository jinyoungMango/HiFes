package hiFes.hiFes.dto.postDto;



import hiFes.hiFes.domain.Picture;
import hiFes.hiFes.domain.Post;
import hiFes.hiFes.domain.festival.OrganizedFestival;
import hiFes.hiFes.domain.user.HostUser;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.festival.OrganizedFestivalResponse;
import lombok.*;

import java.util.List;

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
