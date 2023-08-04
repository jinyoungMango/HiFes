package hiFes.hiFes.controller;


import com.google.gson.JsonObject;
import hiFes.hiFes.domain.HostUser;
import hiFes.hiFes.dto.HostUserSignUpDto;
import hiFes.hiFes.repository.HostUserRepository;
import hiFes.hiFes.service.HostUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HandshakeCompletedEvent;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HostUserController {
    private final HostUserService hostUserService;
    private final HostUserRepository hostUserRepository;

    @CrossOrigin(origins = "*")
    @PostMapping("host/sign-up")
    public String signUp(@RequestBody HostUserSignUpDto hostUserSignUpDto, String accessToken/*, HttpSession session*/) throws Exception{
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);

        hostUserService.signUp(hostUserSignUpDto, context/*, session*/);
        return "signup success";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("host/login")
    public String login(String accessToken /*HttpSession session*/) throws Exception{
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);

        // 만약 받아온 값의 이메일과 추가 정보가 데이터베이스에 있다면 로그인 진행
        if (hostUserRepository.findByEmail((String) context.get("email")).isPresent()) {
            HostUser user = hostUserService.getByEmail((String) context.get("email"));

            // 기업 정보가 저장되지 않았다면 정보 추가 페이지로 이동
            if(hostUserRepository.findByOrgNo(user.getOrgNo()).isPresent()){
                return "기업정보저장되지않음";
            }
//            hostUserService.login((String) context.get("email"), accessToken/*, session*/);
            return "기업정보 저장됨";
        }
        // 없다면 회원가입 진행 일단 엑세스 토큰 저장해두고 리다이렉션?

//        session.setAttribute("access_Token", accessToken);
        return "회원가입실패";

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
    @CrossOrigin(origins = "*")
    @GetMapping("/test")
    public JsonObject test(){
        JsonObject info =new JsonObject();


        info.addProperty("email", 11);
        info.addProperty("name", 12);
        info.addProperty("orgCode", 13);

        return info;
    }


}
