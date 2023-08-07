package hiFes.hiFes.service;


import hiFes.hiFes.ExcelUtils;
import hiFes.hiFes.domain.*;
import hiFes.hiFes.dto.*;
import hiFes.hiFes.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

//@RequiredArgsConstructor
@Service
public class OrganizedFestivalService {
    private final OrganizedFestivalRepository organizedFestivalRepository;
    private final ARItemRepository arItemRepository;
    private final FestivalTableRepository festivalTableRepository;
    private final MarkerRepository markerRepository;
    private final StampMissionRepository stampMissionRepository;



    public OrganizedFestivalService(OrganizedFestivalRepository organizedFestivalRepository,
                                    ARItemRepository arItemRepository,
                                    FestivalTableRepository festivalTableRepository,
                                    MarkerRepository markerRepository,
                                    StampMissionRepository stampMissionRepository){
        this.arItemRepository =arItemRepository;
        this.markerRepository = markerRepository;
        this.stampMissionRepository = stampMissionRepository;
        this.festivalTableRepository = festivalTableRepository;

        this.organizedFestivalRepository =organizedFestivalRepository;
    }


    @Transactional
    public OrganizedFestival save(AddOrganizedFestivalRequest request, MultipartFile file) {
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

    @Transactional
    public OrganizedFestival update(long id, UpdateOrganizedFestivalRequest request) {
        OrganizedFestival organizedFestival = organizedFestivalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        // Update 주최 행사
        organizedFestival.OrganizedFestivalupdate(request.getFestivalId(), request.getFesTitle(), request.getFesOutline(), request.getFesAddress(), request.getFesPosterPath(), request.getFesStartDate(), request.getFesEndDate(),request.getFesLatitude(), request.getFesLongitude());




        // Update 스탬프 미션
        for (UpdateStampMissionRequest stampMissionReq : request.getStampMissions()) {
            System.out.println("스탬프 미션 저장 전 아이디 = " + stampMissionReq.getMissionId());

            StampMission stampMission = stampMissionRepository.findById(stampMissionReq.getMissionId())
                    .orElseThrow(() -> new IllegalArgumentException("Stamp mission not found: " + stampMissionReq.getMissionId()));
            stampMission.update(stampMissionReq);
        }



        // Update AR 아이템
        for (UpdateARItemRequest arItemReq : request.getArItems()) {
            ARItem arItem = arItemRepository.findById(arItemReq.getItemId())
                    .orElseThrow(() -> new IllegalArgumentException("AR item not found: " + arItemReq.getItemId()));
            arItem.update(arItemReq);
        }

        // Update 행사 일정
        for (UpdateFestivalTableRequest festivalScheduleReq : request.getFestivalTables()) {
            FestivalTable festivalTable = festivalTableRepository.findById(festivalScheduleReq.getProgramId())
                    .orElseThrow(() -> new IllegalArgumentException("Festival schedule not found: " + festivalScheduleReq.getProgramId()));
            festivalTable.update(festivalScheduleReq);
        }


        List<UpdateMarkerRequest> markerRequests = request.getMarkers();
        // Update 부스 마커
        for (UpdateMarkerRequest boothMarkerReq : request.getMarkers()) {
            Marker boothMarker = markerRepository.findById(boothMarkerReq.getMarkerId())
                    .orElseThrow(() -> new IllegalArgumentException("Booth marker not found: " + boothMarkerReq.getMarkerId()));
            boothMarker.update(boothMarkerReq);
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


}
