package hiFes.hiFes.Controller;


import hiFes.hiFes.domain.Marker;
import hiFes.hiFes.domain.OrganizedFestival;
import hiFes.hiFes.Service.OrganizedFestivalService;
import hiFes.hiFes.dto.AddOrganizedFestivalRequest;
import hiFes.hiFes.dto.MarkerResponse;
import hiFes.hiFes.dto.OrganizedFestivalResponse;
import hiFes.hiFes.dto.UpdateOrganizedFestivalRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrganizedFestivalApiController {
    private final OrganizedFestivalService organizedFestivalService;

//    @PostMapping("/api/create-festival")
//    public ResponseEntity<String> addOrganizedFestival(@RequestBody AddOrganizedFestivalRequest request, @RequestParam(name="file") MultipartFile file){
//        OrganizedFestival savedOrganizedFestival = organizedFestivalService.save(request,file);
//        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
//    }

    @PostMapping("/api/create-festival")
    public ResponseEntity<String> addOrganizedFestival(@RequestPart("data") AddOrganizedFestivalRequest request, @RequestPart("file") MultipartFile file){
        OrganizedFestival savedOrganizedFestival = organizedFestivalService.save(request,file);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }

    @GetMapping("api/festival/{id}")
    public ResponseEntity<OrganizedFestivalResponse> findOrganizedFestival(@PathVariable long id){
        OrganizedFestival organizedFestival = organizedFestivalService.findById(id);
        return ResponseEntity.ok()
                .body(new OrganizedFestivalResponse(organizedFestival));
    }

    @PutMapping("api/update-festival/{id}")
    public ResponseEntity<OrganizedFestival> updateOrganizedFestival(@PathVariable long id, @RequestBody UpdateOrganizedFestivalRequest request){

        OrganizedFestival updateOrganizedFestival = organizedFestivalService.update(id, request);
        return ResponseEntity.ok()
                .body(updateOrganizedFestival);
    }

    @DeleteMapping("api/delete-aritem/{id}")
    public ResponseEntity<Void> deleteARItem(@PathVariable long id){
        organizedFestivalService.deleteARItem(id);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("api/delete-festival/{id}")
    public ResponseEntity<Void> deleteOrganizedFestival(@PathVariable long id){
        organizedFestivalService.deleteOrganizedFestival(id);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("api/delete-program/{id}")
    public ResponseEntity<Void> deleteFestivalTable(@PathVariable long id){
        organizedFestivalService.deleteFestivalTable(id);
        return ResponseEntity.ok()
                .build();
    }

    @DeleteMapping("api/delete-mission/{id}")
    public ResponseEntity<Void> deleteStampMission(@PathVariable long id){
        organizedFestivalService.deleteStampMission(id);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("api/{id}/festivals")
    public ResponseEntity<List<OrganizedFestivalResponse>> findFestivalByHost(@PathVariable long id){
        List<OrganizedFestival> organizedFestivals = organizedFestivalService.findByHost_hostId(id);
        List<OrganizedFestivalResponse> organizedFestivalResponses = organizedFestivals.stream()
                .map(OrganizedFestivalResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(organizedFestivalResponses);
    }




}
