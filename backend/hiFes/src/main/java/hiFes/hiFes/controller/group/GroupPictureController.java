package hiFes.hiFes.controller.group;

import hiFes.hiFes.domain.group.Group;
import hiFes.hiFes.domain.group.SharedPic;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.group.GroupCreateDto;
import hiFes.hiFes.repository.group.GroupRepository;
import hiFes.hiFes.service.group.GroupPictureService;
import hiFes.hiFes.service.user.JwtService;
import hiFes.hiFes.service.user.NormalUserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GroupPictureController {
    private final JwtService jwtService;
    private final NormalUserService normalUserService;
    private final GroupPictureService groupPictureService;
    private final GroupRepository groupRepository;

    @Operation(summary = "그룹에 사진 업로드", description = "그룹 아이디는 Param으로 보내주세요 /upload?groupId=17 이런 식으로!")
    @PostMapping("group/picture/upload")
    public String groupPictureUpload(HttpServletRequest request,@RequestParam("groupId") Long groupId, @RequestPart("image") MultipartFile image) throws Exception {
        String accessToken = request.getHeader("accessToken");
        String email = jwtService.extractEmail(accessToken).orElse("");
        NormalUser user = normalUserService.getByEmail("test1@test.com");
        Group group = groupRepository.findById(groupId).orElse(null);


        return groupPictureService.GroupPictureUpload(user, group, image);

    }

    @Operation(summary = "그룹 아이디로 그룹에 올라간 사진 조회")
    @GetMapping("group/{groupId}/picture/")
    public List groupPictureCollect(HttpServletRequest request, @PathVariable Long groupId) throws Exception {

        return groupPictureService.GroupPicCollect(groupId);
    }

    @Operation(summary = "그룹에 공유된 사진 ID로 사진 올린 이용자 조회", description = "사진의 id를 올려주셔야 해요 모임아이디 X")
    @GetMapping("group/picture/{sharedPicId}")
    public Long getNormalUserIdsForSharedPic(HttpServletRequest request, @PathVariable Long sharedPicId) throws Exception {
        return groupPictureService.getNormalUserIdsForSharedPic(sharedPicId);
    }
}
