package hiFes.hiFes.Controller;


import hiFes.hiFes.Service.CompletedStampMissionService;
import hiFes.hiFes.Service.GetGiftService;
import hiFes.hiFes.domain.CompletedStampMission;
import hiFes.hiFes.domain.GetGift;
import hiFes.hiFes.domain.OrganizedFestival;
import hiFes.hiFes.dto.CompletedStampMissionResponse;
import hiFes.hiFes.dto.OrganizedFestivalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CompletedStampMissionApiController {

    @Autowired
    private CompletedStampMissionService completedStampMissionService;
    @PostMapping("api/{normalUserId}/complete-mission/{missionId}")
    public CompletedStampMission saveCompletedStampMission(@PathVariable Long normalUserId, @PathVariable Long missionId) {
        return completedStampMissionService.saveCompletedStampMission(normalUserId,missionId);
    }

    @GetMapping("api/{id}/complete-missions")
    public ResponseEntity<List<CompletedStampMissionResponse>> findMissionByNormalUser(@PathVariable Long id){
        List<CompletedStampMission> completedStampMissions = completedStampMissionService.getCompletedStampMissionsByNormalUserId(id);
        List<CompletedStampMissionResponse> completedStampMissionResponses = completedStampMissions.stream()
                .map(CompletedStampMissionResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(completedStampMissionResponses);
    }
}
