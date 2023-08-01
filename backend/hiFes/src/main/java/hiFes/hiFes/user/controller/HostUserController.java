package hiFes.hiFes.user.controller;

import hiFes.hiFes.user.dto.UserSignUpDto;
import hiFes.hiFes.user.service.HostUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HostUserController {
    private final HostUserService hostUserService;

    @PostMapping("/sign-up")
    public String signUp(@RequestBody UserSignUpDto userSignUpDto) throws Exception{
        hostUserService.signUp(userSignUpDto);
        return "가입 완료";
    }

    @GetMapping("/jwt-test")
    public String jwtTest() {
        return "jwtTest 요청 성공";
    }
}
