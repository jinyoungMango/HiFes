package hiFes.hiFes.controller;


import hiFes.hiFes.domain.Marker;
import hiFes.hiFes.service.MarkerService;
import hiFes.hiFes.service.OrganizedFestivalService;
import hiFes.hiFes.dto.MarkerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class MarkerApiController {
    private final MarkerService markerService;
    private final OrganizedFestivalService organizedFestivalService;
    @GetMapping("api/marker/{id}")
    public ResponseEntity<MarkerResponse> findMarker(@PathVariable long id){
        Marker marker = markerService.findById(id);
        return ResponseEntity.ok()
                .body(new MarkerResponse(marker));
    }

    @GetMapping("api/festival/{festivalId}/markers")
    public ResponseEntity<List<MarkerResponse>> findMarkersByFestival(@PathVariable long festivalId){
        List<Marker> markers = markerService.findByFestivalId(festivalId);
        List<MarkerResponse> markerResponses = markers.stream()
                .map(MarkerResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(markerResponses);
    }

    @DeleteMapping("api/delete-marker/{id}")
    public ResponseEntity<Void> deleteMarker(@PathVariable long id){
        organizedFestivalService.deleteMarker(id);

        return ResponseEntity.ok()
                .build();
    }
}
