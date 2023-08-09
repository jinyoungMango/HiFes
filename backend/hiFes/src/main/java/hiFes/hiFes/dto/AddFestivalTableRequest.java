package hiFes.hiFes.dto;

import hiFes.hiFes.domain.FestivalTable;
import hiFes.hiFes.domain.OrganizedFestival;
import lombok.Getter;

import java.time.LocalDateTime;

//@ToString
@Getter
public class AddFestivalTableRequest {
    private String programTitle;
    private String programOutline;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public FestivalTable toEntity(OrganizedFestival organizedFestival){
        return FestivalTable.builder()
                .programOutline(programOutline)
                .programTitle(programTitle)
                .endTime(endTime)
                .startTime(startTime)
                .organizedFestival(organizedFestival)
                .build();
    }
}
