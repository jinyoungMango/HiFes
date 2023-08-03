package hiFes.hiFes.controller;

import hiFes.hiFes.dto.NormalUserSignUpDto;
import hiFes.hiFes.service.NormalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NormalUserController {
    private final NormalUserService normalUserService;

    @PostMapping("normal/sign-up")
    public String signUp(@RequestBody NormalUserSignUpDto normalUserSignUpDto) throws Exception{
        normalUserService.signUp(normalUserSignUpDto);
        return "signup success";
    }

    @PostMapping("kakao/user")
    public String searchKakaoUser(String accessToken){
        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);

        return "login success";
    }
}
