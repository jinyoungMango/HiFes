package hiFes.hiFes.controller.user;


import com.google.gson.JsonObject;
import hiFes.hiFes.domain.user.HostUser;
import hiFes.hiFes.dto.user.HostUserSignUpDto;
import hiFes.hiFes.repository.user.HostUserRepository;
import hiFes.hiFes.service.user.HostUserService;
import hiFes.hiFes.service.user.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HostUserController {
    private final HostUserService hostUserService;
    private final HostUserRepository hostUserRepository;

    private final JwtService jwtService;


//    @CrossOrigin(origins = "*")
//    @PostMapping("host/sign-up")
//    public String signUp(@RequestBody HostUserSignUpDto hostUserSignUpDto/*, String accessToken*/) throws Exception{
//        /*Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);*/
//        System.out.println("aaaaaaaaaaaaaaaaaaa");
//        hostUserService.signUp(hostUserSignUpDto);
////        hostUserService.signUp(hostUserSignUpDto, context)
//        // 로그인
////        hostUserService.loadUserByUsername((String) context.get("email"));
//        hostUserService.login("emailtest1023@test.com");
//        return "signup success";
//    }

    @CrossOrigin(origins = "*")
    @PostMapping("host/sign-up")
    public String signUp(@RequestBody HostUserSignUpDto hostUserSignUpDto) throws Exception{
        String accessToken = hostUserSignUpDto.getAccessToken();
        String test = hostUserSignUpDto.getOrganization();
        System.out.println(accessToken + "***********************************************************" + test);
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);

        hostUserService.signUp(hostUserSignUpDto, context);
        // 로그인
        hostUserService.login((String) context.get("email"));
        return "signup success";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("host/login")
    public Object login(@RequestBody Map<String, String> accessToken ) throws Exception{
        System.out.println(accessToken.get("accessToken") + "++++++++++++++++++++++++++++++++++++++++++++");
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken.get("accessToken"));

        System.out.println("````````````````````````````````````````````````````````````" + hostUserRepository.findByEmail((String) context.get("email")));

        // 만약 받아온 값의 이메일과 추가 정보가 데이터베이스에 있다면 로그인 진행
        if (hostUserRepository.findByEmail((String) context.get("email")).isPresent()) {
            HostUser user = hostUserService.getByEmail((String) context.get("email"));

            System.out.println("????????????????????????????????????????????????????????????" + user.getOrgNo());
            // 기업 정보까지 저장되어 있다면 로그인 진행

            Map<String, String> tokens =  hostUserService.login((String) context.get("email"));

            return tokens;



        }

        return false;

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
    public JsonObject myPage(HttpServletRequest request){
        String accessToken = jwtService.extractAccessToken(request).orElse("");
        String email = jwtService.extractEmail(accessToken).orElse("");
        HostUser user = hostUserService.getByEmail(email);
        JsonObject info =new JsonObject();

        info.addProperty("email", email);
        info.addProperty("name", user.getName());
        info.addProperty("orgNo", user.getOrgNo());
        info.addProperty("orgCode", user.getOrgCode());
        info.addProperty("organization", user.getOrganization());

        return info;


    }


}
