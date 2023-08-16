package hiFes.hiFes.service.festival;



import hiFes.hiFes.ExcelUtils;
import hiFes.hiFes.domain.festival.*;
import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.user.HostUser;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.festival.*;
import hiFes.hiFes.repository.festival.*;
import hiFes.hiFes.repository.group.GroupRepository;
import hiFes.hiFes.repository.group.JoinedGroupRepository;
import hiFes.hiFes.repository.user.HostUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@RequiredArgsConstructor
@Service
@Slf4j
public class OrganizedFestivalService {
    private final OrganizedFestivalRepository organizedFestivalRepository;
    private final ARItemRepository arItemRepository;
    private final FestivalTableRepository festivalTableRepository;
    private final MarkerRepository markerRepository;
    private final StampMissionRepository stampMissionRepository;
    private final HostUserRepository hostUserRepository;
    private final GroupRepository groupRepository;
    private final JoinedGroupRepository joinedGroupRepository;



    public OrganizedFestivalService(OrganizedFestivalRepository organizedFestivalRepository,
                                    ARItemRepository arItemRepository,
                                    FestivalTableRepository festivalTableRepository,
                                    MarkerRepository markerRepository,
                                    StampMissionRepository stampMissionRepository,
                                    HostUserRepository hostUserRepository,
                                    GroupRepository groupRepository,
                                    JoinedGroupRepository joinedGroupRepository
                                    ){
        this.arItemRepository =arItemRepository;
        this.markerRepository = markerRepository;
        this.stampMissionRepository = stampMissionRepository;
        this.festivalTableRepository = festivalTableRepository;
        this.organizedFestivalRepository =organizedFestivalRepository;
        this.hostUserRepository = hostUserRepository;
        this.groupRepository =groupRepository;
        this.joinedGroupRepository = joinedGroupRepository;
    }


    @org.springframework.transaction.annotation.Transactional
    public OrganizedFestival save(AddOrganizedFestivalRequest request, MultipartFile file, MultipartFile image, Long HostUserId) throws Exception {

        System.out.println("파일저장!!!!!!!!!!"+file);
        System.out.println("이미지저장!!!!!!"+image);
        String[] LatLong =  getLatLonFromGoogleApi(request.getFesAddress());
        BigDecimal fesLatitude = new BigDecimal(LatLong[0]);
        BigDecimal fesLongitude = new BigDecimal(LatLong[1]);
        request.setFesLatitude(fesLatitude);
        request.setFesLongitude(fesLongitude);


        //이미지 처리
        String projectPath = "/home/ubuntu/images";
//        String projectPath = System.getProperty("user.dir") +"\\hifes\\src\\main\\resources\\static\\images";
        UUID uuid = UUID.randomUUID();
        String imageName = uuid + "_" + image.getOriginalFilename();
        File saveImage = new File(projectPath, imageName);
        image.transferTo(saveImage);

        request.setFesPosterPath("https://i9d104.p.ssafy.io/images/"+  imageName);

        HostUser hostUser = hostUserRepository.findById(HostUserId).orElseThrow(null);
        request.setHostUser(hostUser);
        OrganizedFestival savedOrganizedFestival = request.toEntity();


        List<AddARItemRequest> arItemRequests = request.getItems();
        if (arItemRequests != null) {

            for (AddARItemRequest arItemReq : request.getItems()) {
                ARItem arItem = arItemReq.toEntity(savedOrganizedFestival);
                savedOrganizedFestival.getArItems().add(arItem);
                arItemRepository.save(arItem);
            }
        }

        if (file!=null && !file.isEmpty())
        {
            try{
                List<FestivalTable> festivalTables = ExcelUtils.readFestivalTable(file.getInputStream());
                for(FestivalTable festivalTable : festivalTables){
                    festivalTable.setOrganizedFestival(savedOrganizedFestival);
                    savedOrganizedFestival.getFestivalTables().add(festivalTable);
                    festivalTableRepository.save(festivalTable);
                }
            } catch(IOException e){
                throw new RuntimeException("엑셀 파일 처리에 실패했습니다. 에러메시지" + e.getMessage());
            }


        }

        List<AddStampMissionRequest> stampMissionRequests = request.getStampMissions();
        if (stampMissionRequests != null) {
            for (AddStampMissionRequest stampMissionReq : request.getStampMissions()) {
                StampMission stampMission = stampMissionReq.toEntity(savedOrganizedFestival);
                savedOrganizedFestival.getStampMissions().add(stampMission);
                stampMissionRepository.save(stampMission);
            }
        }


        List<AddMarkerRequest> markerRequests = request.getMarkers();
        if (markerRequests != null) {
            for (AddMarkerRequest markerReq : request.getMarkers()) {
                Marker marker = markerReq.toEntity(savedOrganizedFestival);
                savedOrganizedFestival.getMarkers().add(marker);
                markerRepository.save(marker);
            }
        }

        return organizedFestivalRepository.save(savedOrganizedFestival);
    }


    //조회
    // 주최자의 모든 행사 리스트 조회
    public List<OrganizedFestivalDetailResponse> findByHost_hostId(long hostId){
        List<OrganizedFestival> organizedFestivals = organizedFestivalRepository.findByHostUser_Id(hostId);
        List<OrganizedFestivalDetailResponse> organizedFestivalDetailResponses = new ArrayList<>();
        for (OrganizedFestival organizedFestival : organizedFestivals) {
            Long festivalId = organizedFestival.getFestivalId();
            Float avgRating = organizedFestivalRepository.getAverageRatingByOrganizedFestival(festivalId);
            if(avgRating==null){
                avgRating = 0f;
            }
            Integer countGroups = groupRepository.findByFestivalId(festivalId).size();
            OrganizedFestivalDetailResponse detailResponse = new OrganizedFestivalDetailResponse(organizedFestival, avgRating, countGroups);
            organizedFestivalDetailResponses.add(detailResponse);
        }

        return organizedFestivalDetailResponses;
    }

    // 행사 상세 조회
    public OrganizedFestivalDetailResponse findById(long id, NormalUser normalUser){
        OrganizedFestival organizedFestival = organizedFestivalRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("not found festival: "+ id));

        //가입 했다면 무슨 모임인가?
        boolean isUserJoined = checkUserJoinedFestival(id, normalUser);

        Float avgRating = organizedFestivalRepository.getAverageRatingByOrganizedFestival(id);
        if(avgRating == null){
            avgRating = 0f;
        }
        Integer countGroups = groupRepository.findByFestivalId(id).size();

        return new OrganizedFestivalDetailResponse(organizedFestival,avgRating, countGroups);
    }
    //랜덤
    public List<OrganizedFestivalDetailResponse> findRandomOrganizedFestival(){
        List<OrganizedFestival> organizedFestivals = organizedFestivalRepository.findAll();
        List<OrganizedFestivalDetailResponse> organizedFestivalDetailResponses = new ArrayList<>();
        for (OrganizedFestival organizedFestival : organizedFestivals) {
            Long festivalId = organizedFestival.getFestivalId();
            Float avgRating = organizedFestivalRepository.getAverageRatingByOrganizedFestival(festivalId);
            if(avgRating==null){
                avgRating = 0f;
            }
            Integer countGroups = groupRepository.findByFestivalId(festivalId).size();
            OrganizedFestivalDetailResponse detailResponse = new OrganizedFestivalDetailResponse(organizedFestival, avgRating, countGroups);
            organizedFestivalDetailResponses.add(detailResponse);
        }
        return organizedFestivalDetailResponses;
    }

    //반경 10km내 조회
    public List<OrganizedFestivalDetailResponse> getFestivalsByLocationWithin10Km(BigDecimal latitude, BigDecimal longitude) {
        List<OrganizedFestival> organizedFestivals = organizedFestivalRepository.findOrganizedFestivalsByLocationWithin10Km(latitude.doubleValue(), longitude.doubleValue());
        List<OrganizedFestivalDetailResponse> organizedFestivalDetailResponses = new ArrayList<>();
        for (OrganizedFestival organizedFestival : organizedFestivals) {
            Long festivalId = organizedFestival.getFestivalId();
            Float avgRating = organizedFestivalRepository.getAverageRatingByOrganizedFestival(festivalId);
            if(avgRating==null){
                avgRating = 0f;
            }
            Integer countGroups = groupRepository.findByFestivalId(festivalId).size();
            OrganizedFestivalDetailResponse detailResponse = new OrganizedFestivalDetailResponse(organizedFestival, avgRating, countGroups);
            organizedFestivalDetailResponses.add(detailResponse);
        }
        return organizedFestivalDetailResponses;
    }

    // 검색 결과
    public List<SearchOrganizedFestivalResponse> searchResultFestival(String word){
        List<OrganizedFestival> titleResults = organizedFestivalRepository.findByFesTitleContaining(word);
        List<OrganizedFestival> addressResults = organizedFestivalRepository.findByFesAddressContaining(word);



        Stream<SearchOrganizedFestivalResponse> titleStream = titleResults.stream()
                .map(organizedFestival -> {
                    Long festivalId = organizedFestival.getFestivalId();
                    Float avgRating = organizedFestivalRepository.getAverageRatingByOrganizedFestival(festivalId);
                    if(avgRating==null){
                        avgRating = 0f;
                    }
                    Integer countGroups = groupRepository.findByFestivalId(festivalId).size();
                    return new SearchOrganizedFestivalResponse(organizedFestival, "title", avgRating, countGroups);
                });

        Stream<SearchOrganizedFestivalResponse> addressStream = addressResults.stream()
                .map(organizedFestival -> {
                    Long festivalId = organizedFestival.getFestivalId();
                    Float avgRating = organizedFestivalRepository.getAverageRatingByOrganizedFestival(festivalId);
                    if(avgRating==null){
                        avgRating = 0f;
                    }
                    Integer countGroups = groupRepository.findByFestivalId(festivalId).size();
                    return new SearchOrganizedFestivalResponse(organizedFestival, "address", avgRating, countGroups);
                });

        List<SearchOrganizedFestivalResponse> results = Stream.concat(titleStream, addressStream)
                .collect(Collectors.toList());

        return results;
    }

    public List<ARItem> findARItemByFestivalId(long festivalId){
        return arItemRepository.findByOrganizedFestival_festivalId(festivalId);
    }

    public List<StampMission> findMissionByFestivalId(long festivalId){
        return stampMissionRepository.findByOrganizedFestival_festivalId(festivalId);
    }

    public List<FestivalTable> findFestivalTableByFestivalId(long festivalId){
        return festivalTableRepository.findByOrganizedFestival_festivalId(festivalId);
    }


////////////////업데이트
    @org.springframework.transaction.annotation.Transactional
    public Boolean update(long id, UpdateOrganizedFestivalRequest request, MultipartFile file, MultipartFile image)throws Exception {
        System.out.println("이미지!!!!!!"+image);
        System.out.println("파일!!!!!"+file);
        OrganizedFestival organizedFestival = organizedFestivalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        String[] LatLong =  getLatLonFromGoogleApi(request.getFesAddress());
        BigDecimal fesLatitude = new BigDecimal(LatLong[0]);
        BigDecimal fesLongitude = new BigDecimal(LatLong[1]);
        request.setFesLatitude(fesLatitude);
        request.setFesLongitude(fesLongitude);
        //update하기 전에 사진 다 삭제하고 다시 넣기
        String projectPath = "/home/ubuntu/images";
//        String projectPath = System.getProperty("user.dir") +"\\hifes\\src\\main\\resources\\static\\images";
        if(!image.isEmpty()){
            String imagePath = projectPath + organizedFestival.getFesPosterPath();
            UUID uuid = UUID.randomUUID();
            String imageName = uuid + "_" + image.getOriginalFilename();
            File saveImage = new File(projectPath, imageName);
            //기존 파일 삭제
            String originImg = organizedFestival.getFesPosterPath();
            new File("/home/ubuntu" + originImg).delete();
            image.transferTo(saveImage);
            request.setFesPosterPath("https://i9d104.p.ssafy.io/images/"+  imageName);
        }

        // Update 주최 행사
        organizedFestival.OrganizedFestivalupdate(request.getFesTitle(), request.getFesOutline(),
                request.getFesAddress(), request.getFesPosterPath(), request.getFesStartDate(),
                request.getFesEndDate(), request.getFesLatitude(), request.getFesLongitude());


        // Update 스탬프 미션
        if (request.getStampMissions() != null){
            for (UpdateStampMissionRequest stampMissionReq : request.getStampMissions()) {

                if (stampMissionReq.getMissionId() == null || stampMissionReq.getMissionId() == 0) {
                    // 새로운 미션 추가
                    StampMission newStampMission = new StampMission();
                    // 사용자가 입력한 데이터로 새 미션 생성
                    newStampMission.update(stampMissionReq);
                    newStampMission.setOrganizedFestival(organizedFestival);
                    // 새 미션 저장
                    stampMissionRepository.save(newStampMission);
                } else {
                    // 기존 스탬프 미션 업데이트
                    StampMission stampMission = stampMissionRepository.findById(stampMissionReq.getMissionId())
                            .orElseThrow(() -> new IllegalArgumentException("Stamp mission not found: " + stampMissionReq.getMissionId()));
                    stampMission.update(stampMissionReq);
                }
            }
        }


//        System.out.println("ar 저장 전 아이디 = " + request.getItems());
//        // Update AR 아이템
//        for (UpdateARItemRequest arItemReq : request.getItems()) {
//
//            if (arItemReq.getItemId() == null || arItemReq.getItemId() == 0) {
//                // 새로운 미션 추가
//                ARItem newArItem = new ARItem();
//                newArItem.update(arItemReq);
//                newArItem.setOrganizedFestival(organizedFestival);
//                // 새 미션 저장
//                arItemRepository.save(newArItem);
//            } else {
//                ARItem arItem = arItemRepository.findById(arItemReq.getItemId())
//                        .orElseThrow(()-> new IllegalArgumentException("arItem not found" + arItemReq.getItemId()));
//            }
//        }

        //업데이트 일정
        if (request.getFestivalTables() != null){
            try {
                List<FestivalTable> newFestivalTableData = ExcelUtils.readFestivalTable(file.getInputStream());
                festivalTableRepository.deleteByOrganizedFestival_festivalId(id);
                for (FestivalTable ft : newFestivalTableData) {
                    ft.setOrganizedFestival(organizedFestival);
                }
                festivalTableRepository.saveAll(newFestivalTableData);
            } catch (IOException e) {
                throw new RuntimeException("Failed to read festival table Excel file.", e);
            }

        }

        if (request.getMarkers() != null){
            for (UpdateMarkerRequest markerReq : request.getMarkers()) {

                if (markerReq.getMarkerId() == null || markerReq.getMarkerId() == 0) {
                    Marker newMarker = new Marker();
                    newMarker.update(markerReq);
                    newMarker.setOrganizedFestival(organizedFestival);
                    markerRepository.save(newMarker);
                } else {
                    Marker marker = markerRepository.findById(markerReq.getMarkerId())
                            .orElseThrow(() -> new IllegalArgumentException("Marker not found" + markerReq.getMarkerId()));
                    marker.update(markerReq);
                }
            }

        }

        boolean flag = true;

        try {
            organizedFestivalRepository.save(organizedFestival);
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            log.error("행사 정보 수정 실패");
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }

    // 삭제 메서드
    public void deleteARItem(long id){
        arItemRepository.deleteById(id);
    }

    public void deleteFestivalTable(long id){
        festivalTableRepository.deleteById(id);
    }

    public void deleteMarker(long id){
        markerRepository.deleteById(id);
    }

    public void deleteOrganizedFestival(long id){
        organizedFestivalRepository.deleteById(id);
    }

    public void deleteStampMission(long id){
        stampMissionRepository.deleteById(id);
    }



    // 행사 관련 모임에 가입했는가?
    public boolean checkUserJoinedFestival(long id, NormalUser normalUser){
        if(normalUser == null){
            return false;
        }
        List<Group> relatedGroups = groupRepository.findByFestivalId(id);

        for(Group group:relatedGroups){
            if (joinedGroupRepository.existsByNormalUserAndGroup(normalUser, group)) {
                return true;
            }
        }
        return false;
    }




    @Transactional
    public String[] getLatLonFromGoogleApi(String fesAddress) {
        String apiKey = "AIzaSyBe3i_41gwVR9efodpGJQuxCx1Qyr1ZDtE";
        String apiUrl = "https://maps.googleapis.com/maps/api/geocode/json";
        // fesAddress를 URL 인코딩합니다.
//        String encodedAddress = URLEncoder.encode(fesAddress, "UTF-8");
//        System.out.println(encodedAddress);
        WebClient webClient = WebClient.builder().build();
        Map<String, Object> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("maps.googleapis.com")
                        .path("/maps/api/geocode/json")
                        .queryParam("address", fesAddress)
                        .queryParam("key", apiKey)
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");

        if (!results.isEmpty()) {
            Map<String, Object> firstResult = results.get(0);
            Map<String, Object> geometry = (Map<String, Object>) firstResult.get("geometry");
            Map<String, Object> location = (Map<String, Object>) geometry.get("location");

            Double latitude = (Double) location.get("lat");
            Double Longitude = (Double) location.get("lng");
            String[] LatLng = new String[2];
            String stringLatitude = Double.toString(latitude);
            String stringLongitude = Double.toString(Longitude);
            LatLng[0] = stringLatitude;
            LatLng[1] = stringLongitude;
            return LatLng;

        } else {
            System.out.println("No results found");
        }
        return null;
    }
}
