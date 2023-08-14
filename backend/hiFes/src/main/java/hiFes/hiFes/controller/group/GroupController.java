package hiFes.hiFes.controller.group;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import hiFes.hiFes.domain.BaseTimeEntity;
import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.group.Hashtag;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.group.GroupCreateDto;
import hiFes.hiFes.dto.group.GroupListDto;
import hiFes.hiFes.repository.user.NormalUserRepository;
import hiFes.hiFes.service.group.GroupService;
import hiFes.hiFes.service.user.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import hiFes.hiFes.service.user.NormalUserService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GroupController extends BaseTimeEntity {
    private final NormalUserService normalUserService;
    private final GroupService groupService;
    private final NormalUserRepository normalUserRepository;
    private final JwtService jwtService;

    // 주석한 부분은 유저 관련 기능이다.

    @PostMapping("group/create")
    public ResponseEntity<String> groupCreate(HttpServletRequest request, @RequestPart("groupCreateDto") GroupCreateDto groupCreateDto, @RequestPart("image")  MultipartFile image) throws Exception {
        String accessToken = request.getHeader("accessToken");
        String email = jwtService.extractEmail(accessToken).orElse("");
        NormalUser user = normalUserService.getByEmail(email);

        groupService.groupCreate(groupCreateDto, user, image);


        return ResponseEntity.ok("group create success");
    }

    @DeleteMapping("group/delete")
    public ResponseEntity<String> groupDelete(HttpServletRequest request, @RequestBody Long id){
        groupService.groupDelete(id.longValue());
        return ResponseEntity.ok("group delete success");
    }


    @GetMapping("group/join/{groupId}")
    public String groupJoin(HttpServletRequest request, @PathVariable Long groupId){
        String accessToken = request.getHeader("accessToken");
        String email = jwtService.extractEmail(accessToken).orElse("");
        NormalUser user = normalUserService.getByEmail(email);
        Group group = groupService.getById(groupId);
        groupService.groupJoin(user, group);
        return "join success";
    }

    @Operation(summary = "그룹 디테일", description = "id에는 그룹 id를 넣어주시면 됩니다. 해당 모임이 참여중인 행사에, 유저가 참여중인 모임이 있는지, 그룹의 리더인지, 참여중인 그룹인지도 함께 반환됩니다.")
    @GetMapping("group/detail/{id}")
    public JsonObject groupDetail(HttpServletRequest request, @PathVariable Long id){
        // 여기서 id는 그룹 아이디
        String email =jwtService.extractEmail(request.getHeader("accessToken")).orElse("");
        NormalUser user = normalUserService.getByEmail(email);

        Group group = groupService.groupDetail(id.longValue());
        List<NormalUser> joinedPeople = groupService.getJoinedPeople(id);

        List<Hashtag> hashtags = groupService.getGroupHashtags(id);
        List hashtagList = groupService.makeHashtagList(hashtags);

        JsonArray hashtagArray = new JsonArray();
        for (Object hashtag : hashtagList) {
            String hashtagS = (String) hashtag;
            hashtagArray.add(hashtagS);
        }


        JsonObject groupInfo =groupService.isJoined(id, user);
        groupInfo.addProperty("groupName", group.getGroupName());
        groupInfo.addProperty("groupContent", group.getContent());
        groupInfo.addProperty("groupMaxPop", group.getMaxPop());
        groupInfo.addProperty("groupCreatedAt", String.valueOf(group.getCreatedAt()));
        groupInfo.addProperty("numOfJoinedPeople", joinedPeople.size());
        groupInfo.addProperty("groupPic", group.getGroupPic());
        groupInfo.add("hashtags", hashtagArray);

        JsonArray joinedPeopleArray = new JsonArray();

        int i = 0;

        for (NormalUser normalUser : joinedPeople) {
            JsonObject userObject = new JsonObject();
            userObject.addProperty("userId", normalUser.getId());
            userObject.addProperty("userNickname", normalUser.getNickname());
            userObject.addProperty("userProfilePic", normalUser.getProfilePic());

            if (i == joinedPeople.size() - 1) {
                userObject.addProperty("isLeader", true);
            }
            else{
                userObject.addProperty("isLeader", false);
            }

            joinedPeopleArray.add(userObject);
            i++;
        }

        groupInfo.add("joinedPeople", joinedPeopleArray);



        return groupInfo;
    }

    @GetMapping("group/list")
    public List groupList(){
        return groupService.getGrouplist();

    }

    @GetMapping("group/list/fes/{festivalId}")
    public List groupFesList(@PathVariable Long festivalId){
        return groupService.getFesGroup(festivalId);

    }


    @GetMapping("group/list/{searchWord}")
    public Map<String, Object> groupSearchList(@PathVariable String searchWord){
        List<GroupListDto> groupList = groupService.getGroupSearch(searchWord);
        Map<String, Object> searchResult = new HashMap<>();
        searchResult.put("searchType", "title");
        searchResult.put("groupList", groupList);

        return searchResult;
    }

    @GetMapping("group/list/hashtag/{searchTag}")
    public Map<String, Object> groupHashtagSearchList(@PathVariable String searchTag){
        List<GroupListDto> groupList = groupService.getGroupHashtagSearch(searchTag);
        Map<String, Object> searchResult = new HashMap<>();
        searchResult.put("searchType", "hashtag");
        searchResult.put("groupList", groupList);
        return searchResult;
    }
}
