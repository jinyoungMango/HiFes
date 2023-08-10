package hiFes.hiFes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UpdateFestivalTableRequest {

    private String programTitle;
    private String programOutline;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long programId;


    public UpdateFestivalTableRequest(Long programId, LocalDateTime startTime, LocalDateTime endTime,
                                      String programTitle, String programOutline){
        this.programId = programId;
        this.programOutline = programOutline;
        this.programTitle = programTitle;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
