package hiFes.hiFes.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PictureDto {

    private String oriFileName;
    private String filePath;
    private Long fileSize;

//    private String oriFileName;
    @Builder
    public PictureDto(String oriFileName, String filePath, Long fileSize) {
        this.oriFileName = oriFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }


}
