package hiFes.hiFes.dto.festival;

import hiFes.hiFes.domain.festival.FestivalTable;
import hiFes.hiFes.domain.festival.OrganizedFestival;
import hiFes.hiFes.domain.festival.ParticipatedFes;
import lombok.Getter;
import lombok.Setter;

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
