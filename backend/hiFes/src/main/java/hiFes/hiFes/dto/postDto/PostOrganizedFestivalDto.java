package hiFes.hiFes.dto.postDto;

import hiFes.hiFes.domain.Post;
import hiFes.hiFes.domain.festival.OrganizedFestival;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class PostOrganizedFestivalDto {
    private Post post;
    private OrganizedFestival organizedFestival;

    public PostOrganizedFestivalDto(Post post, OrganizedFestival organizedFestival) {
        this.post = post;
        this.organizedFestival = organizedFestival;
    }
}