package hiFes.hiFes.controller.festival;


import hiFes.hiFes.domain.festival.EventNotification;
import hiFes.hiFes.domain.festival.ParticipatedFes;
import hiFes.hiFes.domain.festival.StampMission;
import hiFes.hiFes.dto.festival.ParticipatedFesResponse;
import hiFes.hiFes.dto.festival.StampMissionResponse;
import hiFes.hiFes.service.festival.ParticipatedFesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name="일반회원 - 참여행사")
public class ParticipatedFesApiController {

    @Autowired
    private ParticipatedFesService participatedFesService;
    @Operation(summary = "특정 회원 이 행사 참여")
    @PostMapping("/{normalUserId}/participate-festival/{festivalId}")
    public boolean saveParticipateFes(@PathVariable Long normalUserId, @PathVariable Long festivalId){
        return participatedFesService.saveParticipatedFes(normalUserId, festivalId);
    }

    @Operation(summary = "특정 회원이 참여한 행사 정보")
    @GetMapping("/{normalUserId}/participate-festivals")
    public ResponseEntity<List<ParticipatedFesResponse>> findNormalUserParticipatedFest(@PathVariable long normalUserId){
        List<ParticipatedFes> participatedFes = participatedFesService.findNormalUserParticipatedFest(normalUserId);
        List<ParticipatedFesResponse> participatedFesResponses = participatedFes.stream()
                .map(ParticipatedFesResponse :: new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(participatedFesResponses);
    }

//    @Operation(summary = "행사 검색 결과")
//    @GetMapping("/{}")

}
