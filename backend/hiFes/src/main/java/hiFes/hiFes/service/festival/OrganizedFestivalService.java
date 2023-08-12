package hiFes.hiFes.service.festival;



import hiFes.hiFes.ExcelUtils;
import hiFes.hiFes.domain.festival.*;
import hiFes.hiFes.domain.user.HostUser;
import hiFes.hiFes.dto.festival.*;
import hiFes.hiFes.repository.festival.*;
import hiFes.hiFes.repository.user.HostUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

//@RequiredArgsConstructor
@Service
public class OrganizedFestivalService {
    private final OrganizedFestivalRepository organizedFestivalRepository;
    private final ARItemRepository arItemRepository;
    private final FestivalTableRepository festivalTableRepository;
    private final MarkerRepository markerRepository;
    private final StampMissionRepository stampMissionRepository;
    private final HostUserRepository hostUserRepository;



    public OrganizedFestivalService(OrganizedFestivalRepository organizedFestivalRepository,
                                    ARItemRepository arItemRepository,
                                    FestivalTableRepository festivalTableRepository,
                                    MarkerRepository markerRepository,
                                    StampMissionRepository stampMissionRepository,
    HostUserRepository hostUserRepository){
        this.arItemRepository =arItemRepository;
        this.markerRepository = markerRepository;
        this.stampMissionRepository = stampMissionRepository;
        this.festivalTableRepository = festivalTableRepository;
        this.organizedFestivalRepository =organizedFestivalRepository;
        this.hostUserRepository = hostUserRepository;
    }


    @org.springframework.transaction.annotation.Transactional
    public OrganizedFestival save(AddOrganizedFestivalRequest request, MultipartFile file, MultipartFile image, Long HostUserId) throws Exception {

        String[] LatLong =  getLatLonFromGoogleApi(request.getFesAddress());
        BigDecimal fesLatitude = new BigDecimal(LatLong[0]);
        BigDecimal fesLongitude = new BigDecimal(LatLong[1]);
        request.setFesLatitude(fesLatitude);
        request.setFesLongitude(fesLongitude);


        //이미지 처리
        String projectPath = "/home/ubuntu/images";
//        UUID uuid = UUID.randomUUID();
//        String imageName = uuid + "_" + image.getOriginalFilename();
        String imageName = image.getOriginalFilename();
        File saveImage = new File(projectPath, imageName);
        image.transferTo(saveImage);

        request.setFesPosterPath("/images/"+  imageName);

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
    public List<OrganizedFestival> findByHost_hostId(long hostId){
        return organizedFestivalRepository.findByHostUser_Id(hostId);
    }

    public OrganizedFestival findById(long id){
        return organizedFestivalRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found: " + id));
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

    public List<OrganizedFestival> findRandomOrganizedFestival(){
        return organizedFestivalRepository.findAll();
    }

    //반경 10km내 조회
    public List<OrganizedFestival> getFestivalsByLocationWithin10Km(BigDecimal latitude, BigDecimal longitude) {
        return organizedFestivalRepository.findOrganizedFestivalsByLocationWithin10Km(latitude.doubleValue(), longitude.doubleValue());
    }

    // 검색 결과
    public List<OrganizedFestival> searchResultFestival(String word){
        return organizedFestivalRepository.findByFesTitleContaining(word);

    }

    @org.springframework.transaction.annotation.Transactional
    public OrganizedFestival update(long id, UpdateOrganizedFestivalRequest request, MultipartFile file, MultipartFile image)throws Exception {

        OrganizedFestival organizedFestival = organizedFestivalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        String[] LatLong =  getLatLonFromGoogleApi(request.getFesAddress());
        BigDecimal fesLatitude = new BigDecimal(LatLong[0]);
        BigDecimal fesLongitude = new BigDecimal(LatLong[1]);
        request.setFesLatitude(fesLatitude);
        request.setFesLongitude(fesLongitude);
        //update하기 전에 사진 다 삭제하고 다시 넣기
        String projectPath = "/home/ubuntu/images";
        // 이 행사의 포스터 주소 삭제
        // Db에 저장된 포스터 삭제
        if(!image.isEmpty()){
            String imagePath = projectPath + organizedFestival.getFesPosterPath();
            String imageName = image.getOriginalFilename();
            File saveImage = new File(projectPath, imageName);
            if(saveImage.exists()){
                saveImage.delete();
            }
            image.transferTo(saveImage);
            request.setFesPosterPath("/images/"+  imageName);
        }

        // Update 주최 행사
        organizedFestival.OrganizedFestivalupdate(request.getFesTitle(), request.getFesOutline(),
                request.getFesAddress(), request.getFesPosterPath(), request.getFesStartDate(),
                request.getFesEndDate(), request.getFesLatitude(), request.getFesLongitude());


        // Update 스탬프 미션
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
        return organizedFestivalRepository.save(organizedFestival);
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
