package com.example.downgrade.controller;

import com.example.downgrade.config.auth.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class BaseController {

    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model) {

        // CustomOAuth2UerService.java 에서 로그인 성곡 시 세션에 SessionUser 를 저장하도록 구성
        // -> 즉, 로그인 성공 시 httpSession.getAttribute("user") 에서 값을 가져올 수 있다.
        SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if (user != null) {
            model.addAttribute("userName", user.getName());
            model.addAttribute("userImg", user.getPicture());
        }

        return "index";
    }
}
