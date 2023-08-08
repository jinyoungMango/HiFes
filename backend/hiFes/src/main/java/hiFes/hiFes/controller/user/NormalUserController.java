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

@RestController
@RequiredArgsConstructor
public class NormalUserController {
    private final NormalUserService normalUserService;
    private final NormalUserRepository normalUserRepository;
    private final JwtService jwtService;

    @CrossOrigin(origins = "*")
    @PostMapping("normal/sign-up")
    public String signUp(@RequestBody NormalUserSignUpDto normalUserSignUpDto) throws Exception{
        String accessToken = normalUserSignUpDto.getAccessToken();
        String test = normalUserSignUpDto.getNickname();
        System.out.println(accessToken + "***********************************************************" + test);

        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);

        normalUserService.signUp(normalUserSignUpDto, context);

        // 로그인
        normalUserService.login((String) context.get("email"));
        return "signup success";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("normal/login")
    public Boolean login(String accessToken){
        System.out.println(accessToken + "++++++++++++++++++++++++++++++++++++++++++++");
        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);

        // 만약 받아온 값의 이메일과 추가 정보가 데이터베이스에 있다면 로그인 진행
        if (normalUserRepository.findByEmail((String) context.get("email")).isPresent()) {

            normalUserService.login((String) context.get("email"));
            return true;

        }

        return false;

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
