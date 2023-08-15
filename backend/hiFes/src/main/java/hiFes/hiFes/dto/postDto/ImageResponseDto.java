package hiFes.hiFes.dto.postDto;

import lombok.*;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class ImageResponseDto {

    private PostDto postDto;
    private byte[] imageBytes;
    private String imgContentType;

}
