package hiFes.hiFes.controller.user;

import com.google.gson.JsonObject;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.user.NormalUserSignUpDto;
import hiFes.hiFes.repository.user.NormalUserRepository;
import hiFes.hiFes.service.user.JwtService;
import hiFes.hiFes.service.user.NormalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
public class NormalUserController {
    private final NormalUserService normalUserService;
    private final NormalUserRepository normalUserRepository;
    private final JwtService jwtService;

    @CrossOrigin(origins = "*")
    @PostMapping("normal/signUp")
    public JsonObject signUp(@RequestBody NormalUserSignUpDto normalUserSignUpDto) throws Exception{
        String accessToken = normalUserSignUpDto.getAccessToken();
        String test = normalUserSignUpDto.getNickname();
        System.out.println(accessToken + "***********************************************************" + test);

        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);

        normalUserService.signUp(normalUserSignUpDto, context);

        // 로그인
        JsonObject loginSuccess = normalUserService.login((String) context.get("email"));
        loginSuccess.addProperty("result", true);
        loginSuccess.addProperty("id",  normalUserService.getByEmail((String) context.get("email")).getId());
        return loginSuccess;

    }

    @CrossOrigin(origins = "*")
    @PostMapping("normal/login")
    @ResponseBody
    public Object login(String accessToken){
        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);
        if (normalUserRepository.findByEmail((String) context.get("email")).isPresent()) {
            JsonObject loginSuccess = normalUserService.login((String) context.get("email"));
            loginSuccess.addProperty("id",  normalUserService.getByEmail((String) context.get("email")).getId());
            loginSuccess.addProperty("result", true);

            return loginSuccess;
        }

        JsonObject loginFail = new JsonObject();
        loginFail.addProperty("accessToken", "");
        loginFail.addProperty("refreshToken", "");
        loginFail.addProperty("result", false);
        return loginFail;

    }

    @CrossOrigin(origins = "*")
    @PostMapping("normal/fcmSave")
    public Boolean fcmSave(HttpServletRequest request, String fcmToken){
        try{
            String accessToken = jwtService.extractAccessToken(request).orElse("");
            String email = jwtService.extractEmail(accessToken).orElse("");
            NormalUser user = normalUserService.getByEmail(email);

            user.updateFCMToken(fcmToken);

            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    @CrossOrigin(origins = "*")
    @ResponseBody
    @PostMapping("normal/myPage")
    public JsonObject myPage(HttpServletRequest request){
        String accessToken = jwtService.extractAccessToken(request).orElse("");
        String email = jwtService.extractEmail(accessToken).orElse("");
        NormalUser user = normalUserService.getByEmail(email);
        JsonObject info =new JsonObject();

        info.addProperty("email", email);
        info.addProperty("name", user.getName());
        info.addProperty("nickname", user.getNickname());
        info.addProperty("orgCode", user.getProfilePic());

        return info;


    }

}
