package hiFes.hiFes.controller.festival;


import hiFes.hiFes.domain.festival.StampMission;
import hiFes.hiFes.dto.festival.StampMissionResponse;
import hiFes.hiFes.service.festival.CompletedStampMissionService;
import hiFes.hiFes.domain.festival.CompletedStampMission;
import hiFes.hiFes.dto.festival.CompletedStampMissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Tag(name="스탬프 달성 관련 컨트롤러")
public class CompletedStampMissionApiController {

    @Autowired
    private CompletedStampMissionService completedStampMissionService;
    @PostMapping("/{normalUserId}/complete-mission/{missionId}")
    @Operation(summary = "특정 회원이 스탬프 미션 달성")
    public CompletedStampMission saveCompletedStampMission(@PathVariable Long normalUserId, @PathVariable Long missionId) {
        return completedStampMissionService.saveCompletedStampMission(normalUserId,missionId);
    }

//    @GetMapping("api/{normalUserId}/{festivalId}/complete-missions")
//    @Operation(summary = "특정 회원이 행사에서 달성한 미션 목록 조회/")
//    public ResponseEntity<List<CompletedStampMissionResponse>> findFesMissionByNormalUser(@PathVariable Long normalUserId, @PathVariable Long festivalId){
//        List<CompletedStampMission> completedStampMissions = completedStampMissionService.getCompletedMissionIdsByUserIdAndFestivalId(normalUserId, festivalId);
//        List<CompletedStampMissionResponse> completedStampMissionResponses = completedStampMissions.stream()
//                .map(CompletedStampMissionResponse::new)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok()
//                .body(completedStampMissionResponses);
//    }

        @GetMapping("/{normalUserId}/{festivalId}/complete-missions")
        @Operation(summary = "특정 회원이 행사에서 달성한 미션 목록 조회")
        public ResponseEntity<CompletedStampMissionResponse> findFesMissionByNormalUser(@PathVariable Long normalUserId, @PathVariable Long festivalId) {
            List<Long> completedMissionIds = completedStampMissionService.getCompletedMissionIdsByUserIdAndFestivalId(normalUserId, festivalId);
            CompletedStampMissionResponse completedStampMissionResponse = new CompletedStampMissionResponse(festivalId, completedMissionIds);
            return ResponseEntity.ok().body(completedStampMissionResponse);
        }

}
