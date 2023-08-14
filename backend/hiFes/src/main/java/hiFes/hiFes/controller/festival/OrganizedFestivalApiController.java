package hiFes.hiFes.controller.festival;


import hiFes.hiFes.domain.festival.ARItem;
import hiFes.hiFes.domain.festival.FestivalTable;
import hiFes.hiFes.domain.festival.OrganizedFestival;
import hiFes.hiFes.domain.festival.StampMission;
import hiFes.hiFes.dto.festival.*;
import hiFes.hiFes.service.festival.OrganizedFestivalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name="행사 관련 컨트롤러", description = "행사, aritem, 행사일정, 스탬프 미션 관련 CRUD")
public class OrganizedFestivalApiController {
    private final OrganizedFestivalService organizedFestivalService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Operation(summary = "행사 등록", description = "행사, aritem, stampMission, 행사일정(엑셀), 부스 마커를 한번에 등록." +
            "RequestPart를 써서 이미지는 image, 엑셀은 file, 나머지는 data라는 이름을 FormData로 보내야 함. 위경도는 자체 변환되어서 주소만 넣어주면 되고 이미지 주소도 따로 보낼 필요 없음" +
            "이미지 주소 필요 없음. 단, 주소는 실제 있는 주소여야 함(위경도로 변환 해야 해서)")
    @PostMapping("/{hostUserId}/create-festival")
    @CrossOrigin("*")
    public ResponseEntity<String> addOrganizedFestival(@RequestPart("data") AddOrganizedFestivalRequest request, @RequestPart("file") MultipartFile file, @RequestPart("image") MultipartFile image,
                                                       @PathVariable Long hostUserId)
            throws Exception{
        logger.error("trace log ={}", request);
        logger.trace("trace log={}", file);
        logger.trace("trace log={}", image);
        logger.trace("trace log={}", hostUserId);

        OrganizedFestival savedOrganizedFestival = organizedFestivalService.save(request,file,image, hostUserId);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK");
    }


    @Operation(summary = "행사 수정", description = "수정인데 Post요청임을 주의. 특정 행사의 id를 받아서 그 행사 정보, aritem, stampMission, 일정(엑셀), 부스 마커 수정." +
            "Formdata를 사용해서 엑셀은 file, 이미지는 image, 나머지는 data로 받음." +
            "정보 보낼때 어떤 것을 수정하는지 알아야 하므로 각 테이블마다 id 필요, 부스마커-스탬프 미션의 경우 추가적으로 찍히는 것이 수정 과정에 있을 수 있으므로" +
            " id값이 없을 경우 save로직 수행됨")
    @PostMapping("/update-festival/{festivalId}")
    @CrossOrigin("*")
    public Boolean updateOrganizedFestival(@PathVariable long festivalId, @RequestPart("data") UpdateOrganizedFestivalRequest request,
                                           @RequestPart("file") MultipartFile file,
                                           @RequestPart("image") MultipartFile image) throws Exception{
        return organizedFestivalService.update(festivalId, request, file, image);
    }


    @Operation(summary ="특정 aritem 삭제" )
    @DeleteMapping("/delete-aritem/{itemId}")
    @CrossOrigin("*")
    public ResponseEntity<Void> deleteARItem(@PathVariable long itemId){
        organizedFestivalService.deleteARItem(itemId);
        return ResponseEntity.ok()
                .build();
    }
    @Operation(summary ="특정 행사 삭제, 연관된 다른 정보 함께 삭제" )
    @DeleteMapping("/delete-festival/{festivalId}")
    @CrossOrigin("*")
    public ResponseEntity<Void> deleteOrganizedFestival(@PathVariable long festivalId){
        organizedFestivalService.deleteOrganizedFestival(festivalId);
        return ResponseEntity.ok()
                .build();
    }
    @Operation(summary ="특정 행사 일정 삭제" )
    @DeleteMapping("/delete-program/{programId}")
    @CrossOrigin("*")
    public ResponseEntity<Void> deleteFestivalTable(@PathVariable long programId){
        organizedFestivalService.deleteFestivalTable(programId);
        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary = "특정 스탬프 미션 삭제")
    @DeleteMapping("/delete-mission/{missionId}")
    @CrossOrigin("*")
    public ResponseEntity<Void> deleteStampMission(@PathVariable long missionId){
        organizedFestivalService.deleteStampMission(missionId);
        return ResponseEntity.ok()
                .build();
    }


    @Operation(summary ="특정 주최자가 등록한 모든 행사 목록 조회" )
    @GetMapping("/{hostUserId}/festivals")
    @CrossOrigin("*")
    public ResponseEntity<List<OrganizedFestivalResponse>> findFestivalByHost(@PathVariable long hostUserId){
        List<OrganizedFestival> organizedFestivals = organizedFestivalService.findByHost_hostId(hostUserId);
        List<OrganizedFestivalResponse> organizedFestivalResponses = organizedFestivals.stream()
                .map(OrganizedFestivalResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(organizedFestivalResponses);
    }

    @Operation(summary ="특정 행사의 모든 aritem 조회" )
    @GetMapping("/festival/{festivalId}/aritems")
    @CrossOrigin("*")
    public ResponseEntity<List<ARItemResponse>> findARItemByFestivalId(@PathVariable long festivalId){
        List<ARItem> arItems = organizedFestivalService.findARItemByFestivalId(festivalId);
        List<ARItemResponse> arItemResponses = arItems.stream()
                .map(ARItemResponse :: new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(arItemResponses);
    }

    @Operation(summary ="특정 행사의 모든 스탬프미션 조회" )
    @GetMapping("/festival/{festivalId}/missions")
    @CrossOrigin("*")
    public ResponseEntity<List<StampMissionResponse>> findMissionByFestivalId(@PathVariable long festivalId){
        List<StampMission> stampMissions = organizedFestivalService.findMissionByFestivalId(festivalId);
        List<StampMissionResponse> stampMissionResponses = stampMissions.stream()
                .map(StampMissionResponse :: new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(stampMissionResponses);


    }
    @Operation(summary = "특정 행사의 모든 일정 조회")
    @GetMapping("/festival/{festivalId}/festivalTables")
    @CrossOrigin("*")
    public ResponseEntity<List<FestivalTableResponse>> findFestivalTableByFestivalId(@PathVariable long festivalId) {
        List<FestivalTable> festivalTables = organizedFestivalService.findFestivalTableByFestivalId(festivalId);
        List<FestivalTableResponse> festivalTableResponses = festivalTables.stream()
                .map(FestivalTableResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(festivalTableResponses);
    }
    @Operation(summary = "주변 10km내에 있는 행사 목록 조회")
    @GetMapping("/nearby-festivals/{userLatitude}/{userLongitude}")
    @CrossOrigin("*")
    public ResponseEntity<List<OrganizedFestivalResponse>> findNearByFestival(@PathVariable BigDecimal userLatitude, @PathVariable BigDecimal userLongitude){
        List<OrganizedFestival> organizedFestivals = organizedFestivalService.getFestivalsByLocationWithin10Km(userLatitude,userLongitude);
        List<OrganizedFestivalResponse> organizedFestivalResponses = organizedFestivals.stream()
                .map(OrganizedFestivalResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(organizedFestivalResponses);
    }

    @Operation(summary = "특정 행사 조회", description = "특정 행사의 id를 통해 상세 정보 조회")
    @GetMapping("/festival/{festivalId}")
    @CrossOrigin("*")

    public ResponseEntity<OrganizedFestivalDetailResponse> findOrganizedFestival(@PathVariable long festivalId){
        OrganizedFestivalDetailResponse organizedFestival = organizedFestivalService.findById(festivalId);
        return ResponseEntity.ok()
                .body(organizedFestival);
    }


    //행사 리스트에서 랜덤으로 3개 뽑기
    @Operation(summary = "랜덤 행사 목록")
    @GetMapping("/randomFestivals")
    @CrossOrigin("*")
    public ResponseEntity<List<OrganizedFestivalResponse>> findRandomFestivals(){
        List<OrganizedFestival> organizedFestivals =organizedFestivalService.findRandomOrganizedFestival();
        List<OrganizedFestivalResponse> organizedFestivalResponses = organizedFestivals.stream()
                .map(OrganizedFestivalResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(organizedFestivalResponses);
    }

    @Operation(summary = "행사 검색 결과", description = "param으로 key값이 keyword인 검색어를 넣어줘야 합니다.")
    @GetMapping("/search-festival/")
    @CrossOrigin("*")
    public ResponseEntity<List<SearchOrganizedFestivalResponse>> searchFestival(@RequestParam(value = "keyword") String word){
        List<SearchOrganizedFestivalResponse> organizedFestivals = organizedFestivalService.searchResultFestival(word);
        return ResponseEntity.ok()
                .body(organizedFestivals);

    }

}
