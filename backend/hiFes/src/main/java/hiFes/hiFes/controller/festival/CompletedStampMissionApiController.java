package hiFes.hiFes.controller.festival;


import hiFes.hiFes.service.CompletedStampMissionService;
import hiFes.hiFes.domain.CompletedStampMission;
import hiFes.hiFes.dto.CompletedStampMissionResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name="스탬프 달성 관련 컨트롤러")
public class CompletedStampMissionApiController {

    @Autowired
    private CompletedStampMissionService completedStampMissionService;
    @PostMapping("api/{normalUserId}/complete-mission/{missionId}")
    @Operation(summary = "특정 회원이 스탬프 미션 달성")
    public CompletedStampMission saveCompletedStampMission(@PathVariable Long normalUserId, @PathVariable Long missionId) {
        return completedStampMissionService.saveCompletedStampMission(normalUserId,missionId);
    }

    @GetMapping("api/{id}/complete-missions")
    @Operation(summary = "특정 회원이 달성한 모든 스탬프 미션 id 조회/")
    public ResponseEntity<List<CompletedStampMissionResponse>> findMissionByNormalUser(@PathVariable Long id){
        List<CompletedStampMission> completedStampMissions = completedStampMissionService.getCompletedStampMissionsByNormalUserId(id);
        List<CompletedStampMissionResponse> completedStampMissionResponses = completedStampMissions.stream()
                .map(CompletedStampMissionResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(completedStampMissionResponses);
    }
}
