package hiFes.hiFes.dto.festival;

import hiFes.hiFes.domain.festival.FestivalTable;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class FestivalTableResponse {
    private final Long programId;
    private final String programOutline;
    private final String programTitle;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public FestivalTableResponse(FestivalTable festivalTable){
        this.programId = festivalTable.getProgramId();
        this.programOutline = festivalTable.getProgramOutline();
        this.programTitle = festivalTable.getProgramTitle();
        this.startTime = festivalTable.getStartTime();
        this.endTime = festivalTable.getEndTime();
    }

}
