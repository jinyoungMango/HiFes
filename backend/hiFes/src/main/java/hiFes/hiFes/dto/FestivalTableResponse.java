package hiFes.hiFes.dto;

import hiFes.hiFes.domain.FestivalTable;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Getter
public class FestivalTableResponse {

    private final String programOutline;
    private final String programTitle;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public FestivalTableResponse(FestivalTable festivalTable){
        this.programOutline = festivalTable.getProgramOutline();
        this.programTitle = festivalTable.getProgramTitle();
        this.startTime = festivalTable.getStartTime();
        this.endTime = festivalTable.getEndTime();
    }

}
