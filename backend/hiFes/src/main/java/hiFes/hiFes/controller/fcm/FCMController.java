package hiFes.hiFes.controller.fcm;

import hiFes.hiFes.domain.festival.ParticipatedFes;
import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.group.JoinedGroup;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.fcmDto.FCMForGroupDto;
import hiFes.hiFes.dto.fcmDto.FCMForUserDto;
import hiFes.hiFes.repository.festival.ParticipatedFesRepository;
import hiFes.hiFes.repository.group.GroupRepository;
import hiFes.hiFes.repository.group.JoinedGroupRepository;
import hiFes.hiFes.service.fcm.FCMService;
import hiFes.hiFes.service.user.JwtService;
import hiFes.hiFes.service.user.NormalUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class FCMController {
    private final FCMService fcmService;
    private final ParticipatedFesRepository participatedFesRepository;
    private final JoinedGroupRepository joinedGroupRepository;
    private final GroupRepository groupRepository;
    private final JwtService jwtService;
    private final NormalUserService normalUserService;



    @Operation(summary = "주최자가 참여자 전원에게 보냅니다.", description = "필요값 : festivalId, title, detail")
    @CrossOrigin(origins = "*")
    @PostMapping("fcm/for_all")
    public ResponseEntity<String> sendAllUser(@RequestBody FCMForUserDto fcmForUserDto) throws  IOException{
        List<String> fcmTokens = new ArrayList<>();
        List<ParticipatedFes> participatedFesList = participatedFesRepository.findByOrganizedFestivalId(fcmForUserDto.getFestivalId());

        for (ParticipatedFes participatedFes : participatedFesList) {
            NormalUser normalUser = participatedFes.getNormalUser();

            if (normalUser != null) {
                fcmTokens.add(normalUser.getFirebaseToken());
                fcmService.sendMessageTo(normalUser.getFirebaseToken(), fcmForUserDto.getTitle(), fcmForUserDto.getDetail());
            }
        }

        System.out.println(fcmTokens);

        return ResponseEntity.ok("send success");
    }

    @Operation(summary = "그룹 집합 알림을 보냅니다.", description = "필요값 : groupId, location, description, latitude, longitude")
    @CrossOrigin(origins = "*")
    @PostMapping("fcm/for_group")
    public ResponseEntity<String> sendGroupCall(HttpServletRequest request, @RequestBody FCMForGroupDto fcmForGroupDto) throws  IOException{
        String accessToken = request.getHeader("accessToken");
        String email = jwtService.extractEmail(accessToken).orElse("");
        NormalUser user = normalUserService.getByEmail(email);
        Group group = groupRepository.getById(fcmForGroupDto.getGroupId());

        if (!(joinedGroupRepository.findByNormalUserAndGroup(user, group).getIsLeader())){
            return ResponseEntity.ok("모임장이 아닙니다.");
        }

        List<String> fcmTokens = new ArrayList<>();
        List<JoinedGroup> joinedGroupList = joinedGroupRepository.findByGroupId(fcmForGroupDto.getGroupId());

        // 모임 DB에 저장
        group.setGetterLatitude(fcmForGroupDto.getLatitude());
        group.setGetterLongitude(fcmForGroupDto.getLongitude());
        group.setGetterOutline(fcmForGroupDto.getDescription());
        groupRepository.save(group);

        for (JoinedGroup joinedGroup : joinedGroupList) {
            NormalUser normalUser = joinedGroup.getNormalUser();

            if (normalUser != null) {
                fcmTokens.add(normalUser.getFirebaseToken());
                fcmService.sendMessageTo(normalUser.getFirebaseToken(), "모임 집합 콜 : "+ fcmForGroupDto.getLocation() + "에 모여주세요.", fcmForGroupDto.getDescription());
            }
        }

        System.out.println(fcmTokens);

        return ResponseEntity.ok("send success");
    }

    @Operation(summary = "모임 집합 해제")
    @CrossOrigin(origins = "*")
    @DeleteMapping("fcm/group_call_delete/{groupId}")
    public ResponseEntity<String> canselGroupCall(@PathVariable Long groupId) throws IOException {
        Group group = groupRepository.getById(groupId);
        group.setGetterLatitude(null);
        group.setGetterLongitude(null);
        group.setGetterOutline(null);
        groupRepository.save(group);

        return ResponseEntity.ok("cansel success");
    }
}
