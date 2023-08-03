package hiFes.hiFes.controller;


import com.google.gson.JsonObject;
import hiFes.hiFes.dto.HostUserSignUpDto;
import hiFes.hiFes.repository.HostUserRepository;
import hiFes.hiFes.service.HostUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HostUserController {
    private final HostUserService hostUserService;
    private final HostUserRepository hostUserRepository;

    @PostMapping("host/sign-up")
    public String signUp(@RequestBody HostUserSignUpDto hostUserSignUpDto, String accessToken, HttpSession session) throws Exception{
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);

        hostUserService.signUp(hostUserSignUpDto, context, session);
        return "signup success";
    }

    @PostMapping("host/login")
    public String login(String accessToken, HttpSession session) throws Exception{
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);
        // 만약 받아온 값의 이메일이 데이터베이스에 있다면 로그인 진행
        if (hostUserRepository.findByEmail((String) context.get("email")).isPresent()) {
            hostUserService.login((String) context.get("email"), accessToken, session);
            return "login success";
        }
        // 없다면 회원가입 진행 일단 엑세스 토큰 저장해두고 리다이렉션?

//        session.setAttribute("access_Token", accessToken);
        return "redirect:/host/sign-up";

    }

//    @PostMapping("host/logout")
//    public String logout(String accessToken, HttpSession session){
//        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);
//
//        return "logout success";
//    }

    @ResponseBody
    @PostMapping("host/myPage")
    public JsonObject myPage(String accessToken){
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);
//        if ((String) session.getAttribute((String) context.get("email"))){
//         인증된 사용자인지 확인하고 싶었음...
//        }
        JsonObject info =new JsonObject();

        info.addProperty("email", (String) context.get("email"));
        info.addProperty("name", (String) context.get("email"));
        info.addProperty("orgCode", (String) context.get("orgCode"));

        return info;


    }


}
