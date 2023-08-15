package hiFes.hiFes.controller.user;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.user.NormalUserSignUpDto;
import hiFes.hiFes.repository.user.NormalUserRepository;
import hiFes.hiFes.service.user.JwtService;
import hiFes.hiFes.service.user.NormalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NormalUserController {
    private final NormalUserService normalUserService;
    private final NormalUserRepository normalUserRepository;
    private final JwtService jwtService;

    @CrossOrigin(origins = "*")
    @PostMapping("normal/signUp")
    public JsonObject signUp(@RequestPart(value = "normalUserSignUpDto") NormalUserSignUpDto normalUserSignUpDto, @RequestPart(value = "image")  MultipartFile image) throws Exception{
        String accessToken = normalUserSignUpDto.getAccessToken();
        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);

        normalUserService.signUp(normalUserSignUpDto, context, image);

        // 로그인
        JsonObject loginSuccess = normalUserService.login((String) context.get("email"));
        loginSuccess.addProperty("result", true);
        loginSuccess.addProperty("id",  normalUserService.getByEmail((String) context.get("email")).getId());
        loginSuccess.addProperty("nickname", normalUserService.getByEmail((String) context.get("email")).getNickname());
        return loginSuccess;

    }






    @CrossOrigin(origins = "*")
    @PostMapping("normal/login")
    @ResponseBody
    public Object login(String accessToken){
        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);
        if (normalUserRepository.findByEmail((String) context.get("email")).isPresent()) {
            JsonObject loginSuccess = normalUserService.login((String) context.get("email"));
            loginSuccess.addProperty("id",  String.valueOf(normalUserService.getByEmail((String) context.get("email")).getId()));
            loginSuccess.addProperty("result", true);
            loginSuccess.addProperty("nickname", normalUserService.getByEmail((String) context.get("email")).getNickname());

            return loginSuccess;
        }

        JsonObject loginFail = new JsonObject();
        loginFail.addProperty("accessToken", "");
        loginFail.addProperty("refreshToken", "");
        loginFail.addProperty("result", false);
        loginFail.addProperty("id", "");
        loginFail.addProperty("nickname", "");
        return loginFail;

    }

    @CrossOrigin(origins = "*")
    @PostMapping("normal/fcmSave")
    public Boolean fcmSave(HttpServletRequest request,@RequestBody String fcmTokenJson){
        try{
            String accessToken = request.getHeader("accessToken");
            String email = jwtService.extractEmail(accessToken).orElse("");
            NormalUser user = normalUserService.getByEmail(email);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(fcmTokenJson);
            String fcmToken = jsonNode.get("fcmToken").asText();

            user.setFirebaseToken(fcmToken);
            System.out.println(fcmToken);
            normalUserRepository.save(user);

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
        String accessToken = request.getHeader("accessToken");
        String email = jwtService.extractEmail(accessToken).orElse("");
        NormalUser user = normalUserService.getByEmail(email);
        JsonObject info =new JsonObject();

        info.addProperty("email", email);
        info.addProperty("name", user.getName());
        info.addProperty("nickname", user.getNickname());
        info.addProperty("ProfilePic", user.getProfilePic());

        return info;


    }



}
