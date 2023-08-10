package hiFes.hiFes.controller;


import hiFes.hiFes.service.CompletedStampMissionService;
import hiFes.hiFes.domain.CompletedStampMission;
import hiFes.hiFes.dto.CompletedStampMissionResponse;
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
