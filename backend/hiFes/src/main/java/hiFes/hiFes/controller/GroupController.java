package hiFes.hiFes.controller;

import com.google.gson.JsonObject;
import hiFes.hiFes.domain.BaseTimeEntity;
import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.group.GroupCreateDto;
import hiFes.hiFes.dto.group.HashTagDto;
import hiFes.hiFes.repository.user.NormalUserRepository;
import hiFes.hiFes.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hiFes.hiFes.service.user.NormalUserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GroupController extends BaseTimeEntity {
    private final NormalUserService normalUserService;
    private final GroupService groupService;
    private final NormalUserRepository normalUserRepository;

    // 주석한 부분은 유저 관련 기능이다.

    @CrossOrigin(origins = "*")
    @PostMapping("group/create")
    public ResponseEntity<String> groupCreate(String accessToken, @RequestBody GroupCreateDto groupCreateDto, HashTagDto hashTagDto){
        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);
        // 해시태그는 리스트로 온다고 함!!! 아직은 해시태그 하나만 저장 가능

        Optional<NormalUser> optionalUser  = normalUserRepository.findByEmail((String) context.get("email"));
        NormalUser normalUser = optionalUser.orElse(null);
        groupService.groupCreate(groupCreateDto, hashTagDto, normalUser);


        return ResponseEntity.ok("group create success");
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("group/delete")
    public ResponseEntity<String> groupDelete( /*String accessToken*/@RequestBody Long id){
        groupService.groupDelete(id.longValue());
        return ResponseEntity.ok("group delete success");
    }

    @CrossOrigin(origins = "*")
    @GetMapping("group/detail/{id}")
    public JsonObject groupDetail(/*String accessToken*/@PathVariable Long id){
        Group group = groupService.groupDetail(id.longValue());
        JsonObject groupInfo =new JsonObject();
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
}
