package hiFes.hiFes.controller.user;

import hiFes.hiFes.domain.user.HostUser;
import hiFes.hiFes.domain.user.NormalUser;
import hiFes.hiFes.dto.user.NormalUserSignUpDto;
import hiFes.hiFes.repository.user.NormalUserRepository;
import hiFes.hiFes.service.user.NormalUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class NormalUserController {
    private final NormalUserService normalUserService;
    private final NormalUserRepository normalUserRepository;

    @PostMapping("normal/sign-up")
    public String signUp(@RequestBody NormalUserSignUpDto normalUserSignUpDto, String accessToken) throws Exception{
        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);

        normalUserService.signUp(normalUserSignUpDto, context);

        // 로그인
        normalUserService.loadUserByUsername((String) context.get("email"));
        return "signup success";
    }

    @PostMapping("normal/user")
    public String searchKakaoUser(String accessToken){
        Map<String, Object> context =  normalUserService.searchKakaoUser(accessToken);

        // 만약 받아온 값의 이메일과 추가 정보가 데이터베이스에 있다면 로그인 진행
        if (normalUserRepository.findByEmail((String) context.get("email")).isPresent()) {
            NormalUser user = normalUserService.getByEmail((String) context.get("email"));


            normalUserService.loadUserByUsername((String) context.get("email"));
            return "로그인 성공";

        }

        return "로그인실패 회원가입이 필요합니다.";

    }
}
