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
public class HostUserController {
    private final HostUserService hostUserService;
    private final HostUserRepository hostUserRepository;

    private final JwtService jwtService;


    @CrossOrigin(origins = "*")
    @PostMapping("host/sign-up")
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

    @CrossOrigin(origins = "*")
    @PostMapping("host/login")
    @ResponseBody
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

    @CrossOrigin(origins = "*")
    @ResponseBody
    @PostMapping("host/myPage")
    public JsonObject myPage(HttpServletRequest request){
        String email = jwtService.extractEmail(request.getHeader("accessToken")).orElse("");
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
