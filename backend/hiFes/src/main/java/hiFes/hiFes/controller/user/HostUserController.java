package hiFes.hiFes.controller.user;


import com.google.gson.JsonObject;
import hiFes.hiFes.domain.user.HostUser;
import hiFes.hiFes.dto.user.HostUserSignUpDto;
import hiFes.hiFes.repository.user.HostUserRepository;
import hiFes.hiFes.service.user.HostUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HostUserController {
    private final HostUserService hostUserService;
    private final HostUserRepository hostUserRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("host/sign-up")
    public String signUp(@RequestBody HostUserSignUpDto hostUserSignUpDto, String accessToken) throws Exception{
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);

        hostUserService.signUp(hostUserSignUpDto, context);

        // 로그인
        hostUserService.loadUserByUsername((String) context.get("email"));
        return "signup success";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("host/login")
    public String login(String accessToken ) throws Exception{
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);

        // 만약 받아온 값의 이메일과 추가 정보가 데이터베이스에 있다면 로그인 진행
        if (hostUserRepository.findByEmail((String) context.get("email")).isPresent()) {
            HostUser user = hostUserService.getByEmail((String) context.get("email"));

            // 기업 정보까지 저장되어 있다면 로그인 진행
            if(hostUserRepository.findByOrgNo(user.getOrgNo()).isPresent()){
                hostUserService.loadUserByUsername((String) context.get("email"));
                return "로그인 성공";
            }

        }

        return "로그인실패 회원가입이 필요합니다.";

    }

//    @PostMapping("host/logout")
//    public String logout(String accessToken, HttpSession session){
//        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);
//
//        return "logout success";
//    }

    @CrossOrigin(origins = "*")
    @ResponseBody
    @PostMapping("host/myPage")
    public JsonObject myPage(String accessToken){
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);
//        if ((String) session.getAttribute((String) context.get("email"))){
//         인증된 사용자인지 확인하고 싶었음...
//        }
        HostUser user = hostUserService.getByEmail((String) context.get("email"));
        JsonObject info =new JsonObject();

        info.addProperty("email", (String) context.get("email"));
        info.addProperty("name", (String) context.get("name"));
        info.addProperty("orgNo", user.getOrgNo());
        info.addProperty("orgCode", user.getOrgCode());
        info.addProperty("organization", user.getOrganization());

        return info;


    }


}
