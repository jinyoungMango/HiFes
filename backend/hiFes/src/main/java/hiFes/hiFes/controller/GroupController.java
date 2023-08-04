package hiFes.hiFes.controller;

import hiFes.hiFes.domain.BaseTimeEntity;
import hiFes.hiFes.dto.GroupCreateDto;
import hiFes.hiFes.dto.HostUserSignUpDto;
import hiFes.hiFes.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import hiFes.hiFes.service.NormalUserService;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class GroupController extends BaseTimeEntity {
    private final NormalUserService normalUserService;
    private final GroupService groupService;

    // 주석한 부분은 유저 관련 기능이다.

    @CrossOrigin(origins = "*")
    @PostMapping("group/create")
    public ResponseEntity<String> groupCreate(/*String accessToken*/ @RequestBody GroupCreateDto groupCreateDto){
//        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);
        groupService.groupCreate(groupCreateDto);


        return ResponseEntity.ok("group create success");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("group/delete")
    public ResponseEntity<String> groupDelete( /*String accessToken*/ Long id){
        groupService.groupDelete(id.longValue());
        return ResponseEntity.ok("group delete success");
    }

    @CrossOrigin(origins = "*")
    @PostMapping("group/detail")
    public ResponseEntity<String> groupDetail(/*String accessToken*/ Long id){

    }
}
