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
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class HostUserController {
    private final HostUserService hostUserService;
    private final HostUserRepository hostUserRepository;

    private final JwtService jwtService;


    @PostMapping("host/sign-up")
    @CrossOrigin("*")
    public JsonObject  signUp(@RequestBody HostUserSignUpDto hostUserSignUpDto) throws Exception{
        String accessToken = hostUserSignUpDto.getAccessToken();
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);

        hostUserService.signUp(hostUserSignUpDto, context);
        // 로그인
        JsonObject loginSuccess = hostUserService.login((String) context.get("email"));
        loginSuccess.addProperty("result", true);
        loginSuccess.addProperty("id",  hostUserService.getByEmail((String) context.get("email")).getId());
        return loginSuccess;
    }

    @PostMapping("host/login")
    @ResponseBody
    @CrossOrigin("*")
    public Object login(@RequestBody Map<String, String> accessToken ) throws Exception{
        System.out.println(accessToken.get("accessToken") + "++++++++++++++++++++++++++++++++++++++++++++");
        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken.get("accessToken"));

        System.out.println("````````````````````````````````````````````````````````````" + hostUserRepository.findByEmail((String) context.get("email")));

        // 만약 받아온 값의 이메일과 추가 정보가 데이터베이스에 있다면 로그인 진행
        if (hostUserRepository.findByEmail((String) context.get("email")).isPresent()) {
            JsonObject loginSuccess = hostUserService.login((String) context.get("email"));
            loginSuccess.addProperty("result", true);
            loginSuccess.addProperty("id",  hostUserService.getByEmail((String) context.get("email")).getId());


            return loginSuccess;


        }
        JsonObject loginFail = new JsonObject();
        loginFail.addProperty("result", false);


        return loginFail;

    }

//    @PostMapping("host/logout")
//    public String logout(String accessToken, HttpSession session){
//        Map<String, Object> context =  hostUserService.searchKakaoUser(accessToken);
//
//        return "logout success";
//    }

    @ResponseBody
    @PostMapping("host/myPage")
    @CrossOrigin("*")
    public JsonObject myPage(HttpServletRequest request){
//        String email = jwtService.extractEmail(request.getHeader("accessToken")).orElse("");
        String email = jwtService.extractEmail("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSZWZyZXNoVG9rZW4iLCJleHAiOjE2OTI5MzYzNTcsImVtYWlsIjoibGVlaHl1bms2MzEwQG5hdmVyLmNvbSJ9.z55wfot9yO7wSinMaVy8OttFKr0Mv9HgxITUl4CO81f-gFetoKfDjnqeyh6EQVmOkHHj5kpmROdimUFuOQ1Spg").orElse("");
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + email);
        HostUser user = hostUserService.getByEmail(email);
//        HostUser user = hostUserService.getById(id);
        JsonObject info =new JsonObject();

        System.out.println(user.getOrgNo() + " ------------------------------------------");
//        System.out.println(TestUser.getOrgNo() + " ---++++++---------------------------------------");

        info.addProperty("email", user.getEmail());
        info.addProperty("name", user.getName());
        info.addProperty("orgNo", user.getOrg_no());
        info.addProperty("orgCode", user.getOrg_code());
        info.addProperty("organization", user.getOrganization());
        info.addProperty("phoneNo2", user.getPhone_no());
        info.addProperty("phoneNo", user.getPhoneNo());

        return info;


    }




}
