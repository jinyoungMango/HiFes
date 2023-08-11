package hiFes.hiFes.controller.festival;


import hiFes.hiFes.domain.festival.Marker;
import hiFes.hiFes.service.festival.MarkerService;
import hiFes.hiFes.service.festival.OrganizedFestivalService;
import hiFes.hiFes.dto.festival.MarkerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@Tag(name="부스 마커 관련 컨트롤러")
@RequestMapping("/api")
public class MarkerApiController {
    private final MarkerService markerService;
    private final OrganizedFestivalService organizedFestivalService;

    @Operation(summary = "특정 마커 상세 조회")
    @GetMapping("/marker/{markerId}")
    public ResponseEntity<MarkerResponse> findMarker(@PathVariable long markerId){
        Marker marker = markerService.findById(markerId);
        return ResponseEntity.ok()
                .body(new MarkerResponse(marker));
    }

    @Operation(summary = "특정 행사의 모든 마커 조회")
    @GetMapping("/festival/{festivalId}/markers")
    public ResponseEntity<List<MarkerResponse>> findMarkersByFestival(@PathVariable long festivalId){
        List<Marker> markers = markerService.findByFestivalId(festivalId);
        List<MarkerResponse> markerResponses = markers.stream()
                .map(MarkerResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(markerResponses);
    }

    @Operation(summary = "특정 마커 삭제")
    @DeleteMapping("/delete-marker/{id}")
    public ResponseEntity<Void> deleteMarker(@PathVariable long id){
        organizedFestivalService.deleteMarker(id);

        return ResponseEntity.ok()
                .build();
    }
}
