package hiFes.hiFes.controller;

import com.google.gson.JsonObject;
import hiFes.hiFes.domain.BaseTimeEntity;
import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.group.GroupCreateDto;
import hiFes.hiFes.dto.group.HashTagDto;
import hiFes.hiFes.repository.user.NormalUserRepository;
import hiFes.hiFes.service.GroupService;
import hiFes.hiFes.service.user.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hiFes.hiFes.service.user.NormalUserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GroupController extends BaseTimeEntity {
    private final NormalUserService normalUserService;
    private final GroupService groupService;
    private final NormalUserRepository normalUserRepository;
    private final JwtService jwtService;

    // 주석한 부분은 유저 관련 기능이다.

    @CrossOrigin(origins = "*")
    @PostMapping("group/create")
    public ResponseEntity<String> groupCreate(HttpServletRequest request, GroupCreateDto groupCreateDto,  @RequestPart("image") MultipartFile image) throws Exception {
        String accessToken = jwtService.extractAccessToken(request).orElse("");
        String email = jwtService.extractEmail(accessToken).orElse("");
        NormalUser user = normalUserService.getByEmail(email);

        groupService.groupCreate(groupCreateDto, user, image);


        return ResponseEntity.ok("group create success");
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("group/delete")
    public ResponseEntity<String> groupDelete(HttpServletRequest request, @RequestBody Long id){
        groupService.groupDelete(id.longValue());
        return ResponseEntity.ok("group delete success");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("group/join")
    public String groupJoin(HttpServletRequest request, @RequestBody Long groupId){
        String accessToken = jwtService.extractAccessToken(request).orElse("");
        String email = jwtService.extractEmail(accessToken).orElse("");
        NormalUser user = normalUserService.getByEmail(email);
        Group group = groupService.getById(groupId);
        groupService.groupJoin(user, group);
        return "join success";
    }

    @Operation(summary = "그룹 디테일", description = "id에는 그룹 id를 넣어주시면 됩니다. 해당 모임이 참여중인 행사에, 유저가 참여중인 모임이 있는지, 그룹의 리더인지, 참여중인 그룹인지도 함께 반환됩니다.")
    @CrossOrigin(origins = "*")
    @GetMapping("group/detail/{id}")
    public JsonObject groupDetail(HttpServletRequest request, @PathVariable Long id){
        // 여기서 id는 그룹 아이디
        String accessToken = jwtService.extractAccessToken(request).orElse("");
        String email = jwtService.extractEmail(accessToken).orElse("");
        NormalUser user = normalUserService.getByEmail(email);

        Group group = groupService.groupDetail(id.longValue());


        JsonObject groupInfo =groupService.isJoined(id, user);
        groupInfo.addProperty("groupName", group.getGroupName());
        groupInfo.addProperty("groupContent", group.getContent());
        groupInfo.addProperty("groupMaxPop", group.getMaxPop());
        groupInfo.addProperty("groupCreatedAt", String.valueOf(group.getCreatedAt()));


        return groupInfo;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("group/list")
    public List groupList(){
        List<Group> groupList = groupService.getGrouplist();

        return groupList;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("group/list/{searchWord}")
    public List groupSearchList(@PathVariable String searchWord){
        List<Group> groupList = groupService.getGroupSearch(searchWord);

        return groupList;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("group/list/hashtag/{searchTag}")
    public List groupHashtagSearchList(@PathVariable String searchTag){
        List<Group> groupList = groupService.getGroupHashtagSearch(searchTag);

        return groupList;
    }
}
